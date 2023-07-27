plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kapt)
    alias(libs.plugins.dagger.hilt.android)
}

android {
    namespace = "com.odisby.copa_feminina.data.local"
    compileSdk = rootProject.extra["compileSdkVersion"] as Int
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data:data"))

    implementation(libs.androidx.datastore)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}