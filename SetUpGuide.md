# Guide to setup for various platforms

## Before running!
 - check your system with [KDoctor](https://github.com/Kotlin/kdoctor)
 - install JDK 17 or higher on your machine

### Android
To run the application on android device/emulator:  
 - open project in Android Studio and run imported android run configuration

To build the application bundle:
 - run `./gradlew :composeApp:assembleDebug`
 - find `.apk` file in `composeApp/build/outputs/apk/debug/composeApp-debug.apk`
Run android UI tests on the connected device: `./gradlew :composeApp:connectedDebugAndroidTest`

### Desktop
Run the desktop application: `./gradlew :composeApp:run`
Run desktop UI tests: `./gradlew :composeApp:jvmTest`

### iOS
To run the application on iPhone device/simulator:
 - Open `iosApp/iosApp.xcproject` in Xcode and run standard configuration
 - Or use [Kotlin Multiplatform Mobile plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile) for Android Studio
Run iOS simulator UI tests: `./gradlew :composeApp:iosSimulatorArm64Test`

### JS Browser (Experimental)
Run the browser application: `./gradlew :sample:composeApp:jsBrowserDevelopmentRun --continue`
Run browser UI tests: `./gradlew :composeApp:jsBrowserTest`

### Wasm Browser (Alpha)
Run the browser application: `./gradlew :sample:composeApp:wasmJsBrowserDevelopmentRun --continue`
Run browser UI tests: `./gradlew :composeApp:wasmJsBrowserTest`
