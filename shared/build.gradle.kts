import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.Framework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            // put your Multiplatform dependencies here
        }

        targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget>().all {
            val mainCompilation = compilations.getByName("main")
            mainCompilation.cinterops.create("swift_expect_declaration") {
                includeDirs("$rootDir/shared_header")
            }
        }
    }

    linkerConfig(
        "-U _OBJC_CLASS_\$_SwiftExpectDeclaration"
    )
}

android {
    namespace = "dev.srsrouza.swift.interop.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

private fun KotlinMultiplatformExtension.linkerConfig(linkerOpts: String) {
    targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java)
        .map { target ->
            val mainCompilation = target.compilations.getByName("main")
            val dynamicFrameworks =
                target.binaries.filterIsInstance<Framework>().filter { framework -> !framework.isStatic }

            Pair(mainCompilation, dynamicFrameworks)
        }
        .forEach { pair ->
            if (!pair.second.isEmpty()) {
                pair.first.kotlinOptions.freeCompilerArgs += listOf("-linker-options", linkerOpts)
            }
        }
}