plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.tenkovskaya.testcomposeprogect"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tenkovskaya.testcomposeprogect"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom.v20240400))
    //noinspection UseTomlInstead
//    implementation("androidx.compose.ui:ui")
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.material3.android)
    testImplementation(libs.junit)
    //noinspection UseTomlInstead
//    implementation("androidx.compose.ui:ui-graphics")
    //noinspection UseTomlInstead
//    implementation("androidx.compose.ui:ui-tooling-preview")
    //noinspection UseTomlInstead
//    implementation("androidx.compose.material3:material3")
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //noinspection UseTomlInstead
//    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    //noinspection UseTomlInstead
//    debugImplementation("androidx.compose.ui:ui-tooling")
    //noinspection UseTomlInstead
//    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation (libs.androidx.material)
    implementation(libs.androidx.ads.identifier)
    implementation(libs.guava)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.guava)
}