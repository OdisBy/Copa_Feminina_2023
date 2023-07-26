plugins {
    id("com.android.library")
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.odisby.copa_feminina.data.local"
    compileSdk = rootProject.extra["compileSdkVersion"] as Int

    defaultConfig {
        minSdk = rootProject.extra["minSdkVersion"] as Int
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
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data:data"))

    implementation(libs.androidx.datastore)

    implementation(libs.dagger.compiler)
    implementation(libs.dagger)
}