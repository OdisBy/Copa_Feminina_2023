plugins {
    alias(libs.plugins.kotlin.kapt)
    java
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(libs.coroutines.core)

    implementation(libs.dagger.compiler)
    implementation(libs.dagger)
}