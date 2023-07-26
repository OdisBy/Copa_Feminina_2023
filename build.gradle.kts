buildscript {
    extra.apply{
        set("minSdkVersion", 26)
        set("targetSdkVersion", 33)
        set("compileSdkVersion", 33)
    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.dagger.hilt.android) apply false
    alias(libs.plugins.kapt)
    alias(libs.plugins.androidLibrary) apply false
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}

allprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }
}