@file:Suppress("DEPRECATION_ERROR")
@file:OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)

import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose)
    alias(libs.plugins.android.kmp.library)
    alias(libs.plugins.maven.publishing)
}

kotlin {
    jvmToolchain(21)

    compilerOptions {
        optIn.add("kotlin.time.ExperimentalTime")
    }

    android {
        namespace = "com.arjunjadeja.texty"
        compileSdk = 37
        minSdk = 23
        androidResources {
            enable = true
        }
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm()

    js {
        browser()
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "texty"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.datetime)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(compose.uiTest)
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.arjunjadeja.texty.desktopApp"
            packageVersion = "1.0.0"
        }
    }
}

mavenPublishing {
    coordinates(
        groupId = "com.arjunjadeja",
        artifactId = "texty",
        version = "1.0.0-alpha"
    )

    pom {
        name.set("Texty")
        description.set("A Jetpack Compose Multiplatform Library to display text with various styles, effects and animations")
        inceptionYear.set("2024")
        url.set("https://github.com/arjunjadeja/texty/")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("arjunjadeja")
                name.set("Arjun Jadeja")
                url.set("https://github.com/arjunjadeja/")
            }
        }
        scm {
            url.set("https://github.com/arjunjadeja/texty/")
            connection.set("scm:git:git://github.com/arjunjadeja/texty.git")
            developerConnection.set("scm:git:ssh://git@github.com/arjunjadeja/texty.git")
        }
    }

    val secretKeyRingFile = providers.gradleProperty("signing.secretKeyRingFile")
        .orNull
        ?.let(::file)
    if (secretKeyRingFile != null && secretKeyRingFile.isFile) {
        signAllPublications()
    }
}
