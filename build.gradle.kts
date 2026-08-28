import org.gradle.api.tasks.Exec
import org.gradle.process.ExecOperations
import javax.inject.Inject

plugins {
    alias(libs.plugins.multiplatform).apply(false)
    alias(libs.plugins.compose.compiler).apply(false)
    alias(libs.plugins.compose).apply(false)
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.kmp.library).apply(false)
}

tasks.register("runDesktop") {
    group = "run"
    description = "Run the sample as a JVM desktop window"
    dependsOn(":sample:composeApp:run")
}

tasks.register("runWeb") {
    group = "run"
    description = "Run the sample in a browser (Compose for Web / Wasm)"
    dependsOn(":sample:composeApp:wasmJsBrowserDevelopmentRun")
}

tasks.register("runJs") {
    group = "run"
    description = "Run the sample in a browser (Kotlin/JS)"
    dependsOn(":sample:composeApp:jsBrowserDevelopmentRun")
}

abstract class RunAndroidTask @Inject constructor(
    private val execOps: ExecOperations,
) : DefaultTask() {
    @TaskAction
    fun launch() {
        execOps.exec {
            commandLine(
                adbExecutable(),
                "shell",
                "am",
                "start",
                "-n",
                "com.arjunjadeja.texty.androidApp/com.arjunjadeja.texty.AppActivity",
            )
        }
    }

    private fun adbExecutable(): String {
        val androidHome = System.getenv("ANDROID_HOME")
            ?: System.getenv("ANDROID_SDK_ROOT")
            ?: "${System.getProperty("user.home")}/Library/Android/sdk"
        val adb = File("$androidHome/platform-tools/adb")
        return if (adb.exists()) adb.absolutePath else "adb"
    }
}

tasks.register<RunAndroidTask>("runAndroid") {
    group = "run"
    description = "Install and launch the sample on a connected Android device or emulator"
    dependsOn(":sample:androidApp:installDebug")
    notCompatibleWithConfigurationCache("Starts the app with adb")
}

tasks.register<Exec>("runIos") {
    group = "run"
    description = "Build and launch the sample on the iOS Simulator"
    workingDir = rootDir
    notCompatibleWithConfigurationCache("Boots a simulator and runs xcodebuild")
    commandLine("bash", "-lc", iosSimulatorLaunchScript)
    doFirst {
        check(System.getProperty("os.name").orEmpty().contains("Mac")) {
            "runIos is only supported on macOS"
        }
    }
}

private val iosSimulatorLaunchScript = """
    set -euo pipefail

    simulator_id="${'$'}(python3 - <<'PY'
    import json, subprocess, sys
    data = json.loads(subprocess.check_output(["xcrun", "simctl", "list", "devices", "available", "-j"]))
    devices = [d for runtime in data.get("devices", {}).values() for d in runtime]
    iphones = [d for d in devices if d.get("isAvailable") and "iPhone" in d.get("name", "")]
    booted = next((d["udid"] for d in iphones if d.get("state") == "Booted"), None)
    if booted:
        print(booted)
        sys.exit(0)
    if not iphones:
        sys.exit("No available iPhone simulator found. Install one in Xcode.")
    print(iphones[0]["udid"])
    PY
    )"

    state="${'$'}(xcrun simctl list devices | grep "${'$'}simulator_id" | tail -1 || true)"
    if ! echo "${'$'}state" | grep -q "Booted"; then
      xcrun simctl boot "${'$'}simulator_id"
    fi
    open -a Simulator

    xcodebuild \
      -project sample/iosApp/iosApp.xcodeproj \
      -scheme iosApp \
      -configuration Debug \
      -destination "id=${'$'}simulator_id" \
      -derivedDataPath build/ios \
      build

    app_path="${'$'}(find build/ios/Build/Products/Debug-iphonesimulator -name '*.app' | head -1)"
    if [ -z "${'$'}app_path" ]; then
      echo "Could not find the built .app under build/ios"
      exit 1
    fi

    xcrun simctl install "${'$'}simulator_id" "${'$'}app_path"
    xcrun simctl launch "${'$'}simulator_id" com.arjunjadeja.texty.iosApp
""".trimIndent()
