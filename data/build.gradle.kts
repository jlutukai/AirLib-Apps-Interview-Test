import java.util.Properties

plugins {
    id("airlibs.android.library")
    id("airlibs.android.hilt")
    id("airlibs.android.room")
}

val properties = Properties().apply {
    load(project.rootProject.file("local.properties").inputStream())
}
//val BASE_URL: String = properties.getProperty("BASE_URL")
//val SENTRY_DSN = properties.getProperty("SENTRY_DSN")
android {
    namespace = "com.airlibs.data"
    buildFeatures {
        buildConfig = true
    }
    defaultConfig{
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://run.mocky.io\"")
//            buildConfigField("String", "SENTRY_DSN", SENTRY_DSN)
        }
        release {
            buildConfigField("String", "BASE_URL", "\"https://run.mocky.io\"")
//            buildConfigField("String", "SENTRY_DSN", SENTRY_DSN)
        }
    }
}

dependencies {

    implementation(project(":domain"))
    implementation(libs.androidx.junit.ktx)
    implementation(libs.androidx.runner)
    testImplementation(libs.junit4)
//    androidTestImplementation(libs.junit)
//    androidTestImplementation(libs.es)

    implementation(libs.datastore.preferences)
//    implementation(libs.protobuf.kotlin.lite)
    implementation(libs.androidx.datastore)

    debugImplementation(libs.chucker.debug)
    releaseImplementation(libs.chucker)
//    implementation(libs.sentry.android)

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gson.converter)
    implementation(libs.okhttp.logging)
    implementation(libs.okhttp)
    implementation(libs.mockwebserver)
    testImplementation (libs.androidx.core.testing)
    androidTestImplementation (libs.androidx.core.testing)
    androidTestImplementation (libs.kotlinx.coroutines.test)
    testImplementation (libs.kotlinx.coroutines.test)

    testImplementation (libs.hilt.android.testing)
    kspTest (libs.hilt.compiler)
    androidTestImplementation (libs.hilt.android.testing)
    kspAndroidTest (libs.hilt.compiler)

    androidTestUtil( libs.androidx.orchestrator)
    androidTestImplementation (libs.androidx.runner)
    androidTestImplementation (libs.androidx.rules)
    implementation(libs.timber)
}