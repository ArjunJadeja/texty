# Guide to setup for various platforms

I have made the project in Android Studio Ladybug Feature Drop | 2024.2.2 Canary 2

If you are facing build issues and have older version
then [check Android Gradle plugin and Android Studio compatibility](https://developer.android.com/build/releases/gradle-plugin#android_gradle_plugin_and_android_studio_compatibility)

## Before running!

- check your system with [KDoctor](https://github.com/Kotlin/kdoctor)
- install JDK 17 or higher on your machine (the project toolchain uses JDK 21)

See [docs/TESTING.md](docs/TESTING.md) for the full testing stack, CI/CD flow, and Gradle command reference.

### Android

To run the application on android device/emulator:

- open project in Android Studio and run imported android run configuration

To build the application bundle:

- run `./gradlew :sample:androidApp:assembleDebug`
- find `.apk` under `sample/androidApp/build/outputs/apk/debug/`

Instrumented UI tests are not configured yet; use JVM `commonTest` (see Testing doc).

### Desktop

Run the desktop application: `./gradlew runDesktop` or `./gradlew :sample:composeApp:run`

Run desktop UI tests: `./gradlew :sample:composeApp:jvmTest`

Run all CI-equivalent tests: `./gradlew testAll`

### iOS (I am yet to try this, if it is working fine please let me know)

To run the application on iPhone device/simulator:

- Open `sample/iosApp/iosApp.xcodeproj` in Xcode and run standard configuration
- Or use [Kotlin Multiplatform Mobile plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile) for Android Studio

Run iOS simulator UI tests: `./gradlew :sample:composeApp:iosSimulatorArm64Test`

### JS Browser (Experimental)

Run the browser application: `./gradlew :sample:composeApp:jsBrowserDevelopmentRun --continue`

Run browser UI tests: `./gradlew :sample:composeApp:jsBrowserTest`

### Wasm Browser (Alpha)

Run the browser application: `./gradlew :sample:composeApp:wasmJsBrowserDevelopmentRun --continue`

Run browser UI tests: `./gradlew :sample:composeApp:wasmJsBrowserTest`
