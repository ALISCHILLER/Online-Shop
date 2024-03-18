plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)

    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.msa.onlineshopzar"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.msa.onlineshopzar"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    // Allow references to generated code
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //hilt
    implementation(dependency.hilt.android)
    kapt(dependency.hilt.android.compiler)
    implementation(dependency.androidx.hilt.navigation.compose)

    //retrofit
    implementation (dependency.retrofit)
    implementation (dependency.converter.gson)
    //moshi
    implementation(dependency.moshi.kotlin)
    implementation(dependency.converter.moshi)

    // ViewModel utilities for Compose
    implementation(dependency.androidx.lifecycle.viewmodel.ktx)
    implementation(dependency.androidx.lifecycle.viewmodel.compose)
    // Lifecycle utilities for Compose
    implementation(dependency.androidx.lifecycle.runtime.compose)

    //timber
    implementation(dependency.timber)


    //Room Db
    implementation(dependency.androidx.room.runtime)
    annotationProcessor(dependency.androidx.room.compiler)
    //material.icons
    implementation(dependency.androidx.material.icons.extended.android)
    //androidx.navigation
    implementation(dependency.androidx.navigation.compose)

    //coil loading image
    implementation(dependency.coil.compose)
}