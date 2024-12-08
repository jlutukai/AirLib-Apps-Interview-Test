plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

dependencies {

    implementation(libs.coroutines.core)
    // Paging 3
//    implementation(libs.androidx.paging.common.ktx)
//    implementation(libs.datastore.preferences.core)
    api(libs.retrofit.gson.converter)
    implementation(libs.javax)
    implementation(libs.androidx.annotation.jvm)
}