plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kapt)
    alias(libs.plugins.dagger.hilt.android)
}

android {
    namespace = "com.odisby.copa_feminina.data.remote"
    compileSdk = rootProject.extra["compileSdkVersion"] as Int
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data:data"))

    implementation(libs.androidx.datastore)
    implementation(libs.bundles.retrofit)
    implementation(libs.squareup.okttp3.logging.interceptor)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}