plugins {
    id("kotlinx-serialization")
    id("kotlin-kapt")
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
//    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.anos.network"
    compileSdk = 34
    buildFeatures {
        buildConfig = true
    }
    defaultConfig {
        buildConfigField("String", "BASE_URL", "\"https://vnexpress.net\"")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

//secrets {
//    defaultPropertiesFileName = "secrets.defaults.properties"
//}

dependencies {
    implementation(project(":core:model"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.retrofit.converter.simplexml)

    implementation(libs.android.hilt)
    kapt(libs.android.hilt.compiler)
}