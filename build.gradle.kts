// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
}

buildscript {
    extra.apply{
        set("minSdkVersion", 26)
        set("targetSdkVersion", 33)
        set("compileSdkVersion", 33)
    }
}