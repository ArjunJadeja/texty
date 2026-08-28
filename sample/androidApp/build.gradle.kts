@file:Suppress("DEPRECATION_ERROR")

import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.arjunjadeja.texty.sample"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.arjunjadeja.texty.androidApp"
        minSdk = 23
        targetSdk = 37
        versionCode = 1
        versionName = "1.0.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_11)
    }
}

dependencies {
    implementation(project(":sample:composeApp"))
    implementation(libs.androidx.activityCompose)
    implementation("org.jetbrains.compose.ui:ui-tooling-preview:${libs.versions.compose.get()}")
    debugImplementation("org.jetbrains.compose.ui:ui-tooling:${libs.versions.compose.get()}")
}
