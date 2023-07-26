plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlin.kapt)
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

    implementation(libs.dagger.compiler)
    implementation(libs.dagger)
}