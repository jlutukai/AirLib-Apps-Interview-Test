plugins {
//    alias(libs.plugins.androidApplication)
//    alias(libs.plugins.kotlinAndroid)
    id("airlibs.android.application")
    id("airlibs.android.application.compose")
    id("airlibs.android.hilt")
    kotlin("plugin.serialization") version "2.0.21"
    id("io.sentry.android.gradle") version "4.11.0"
}

android {
    namespace = "com.airlibs.app"

    defaultConfig {
        applicationId = "com.airlibs.app"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(libs.androidx.compose.activity)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    testImplementation(libs.junit)
    testImplementation (libs.mockito.core)
    androidTestImplementation(libs.androidx.junit)
    implementation(libs.lottie.compose)
    implementation(libs.timber)
}