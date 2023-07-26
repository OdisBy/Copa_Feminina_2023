plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kapt)
}

android {
    namespace = "com.odisby.copa_feminina.data.data"
    compileSdk = rootProject.extra["compileSdkVersion"] as Int
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.coroutines.core)
    implementation(libs.dagger.compiler)
    implementation(libs.dagger)
}