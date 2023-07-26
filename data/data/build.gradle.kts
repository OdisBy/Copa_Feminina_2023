plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.odisby.copa_feminina.data.data"
    compileSdk = rootProject.extra["compileSdkVersion"] as Int
}

dependencies {
    implementation(libs.coroutines.core)

    implementation(libs.dagger.compiler)
    implementation(libs.dagger)
}