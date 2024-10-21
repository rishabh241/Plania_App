import com.google.protobuf.Api
import dev.icerock.gradle.MRVisibility
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    id("dev.icerock.mobile.multiplatform-resources")

    id("com.google.gms.google-services")
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
            baseName = "ComposeApp"
            export("dev.icerock.moko:resources:0.24.2")
            export("dev.icerock.moko:graphics:0.24.2")
        }
    }

    sourceSets {

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation("io.ktor:ktor-client-android:2.3.12")

            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
            implementation(project.dependencies.platform(libs.android.firebase.bom))
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)

            implementation(libs.voyager)
            implementation(libs.voyager.tabNavigator)
            implementation(libs.voyager.transitions)


            implementation("com.kizitonwose.calendar:compose-multiplatform:2.5.4")

            implementation("media.kamel:kamel-image:0.9.5")
            implementation("io.ktor:ktor-client-core:2.3.12")

            implementation("dev.icerock.moko:resources:0.24.2")

            implementation("br.com.devsrsouza.compose.icons:font-awesome:1.1.1")
            implementation("com.google.firebase:firebase-analytics")

            // https://mvnrepository.com/artifact/com.google.firebase/firebase-database
            implementation("com.google.firebase:firebase-database:21.0.0")


            api(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.composeVM)

            implementation(project.dependencies.platform(libs.android.firebase.bom))
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)



        }
        iosMain.dependencies {
            implementation("io.ktor:ktor-client-darwin:2.3.12")
        }
    }
}

android {
    namespace = "com.kmp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.kmp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }

}
multiplatformResources {
    multiplatformResources {
        resourcesPackage.set("com.kmp.VoyagerProject") // required
        resourcesClassName.set("SharedRes") // optional, default MR
    }
}

