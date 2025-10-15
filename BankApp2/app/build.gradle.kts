plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    // Google Services Gradle plugin
    //id("com.android.application")
    id("com.google.gms.google-services") version "4.4.2"
}

android {
    namespace = "com.example.bank_app"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.bank_app"
        minSdk = 23
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("release") {
            isCrunchPngs = true // Ensure this is true or not explicitly false
            isMinifyEnabled = true    // Enables code shrinking, obfuscation, and optimization
            isShrinkResources = true // Removes unused resources (requires minifyEnabled)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro" // Your custom ProGuard rules
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
//    implementation(libs.androidx.navigation.compose)
    implementation("com.google.firebase:firebase-database-ktx:20.2.0")
//    implementation(libs.androidx.compose.ui.viewbinding)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)


    // Material Compose Library
    //implementation("androidx.compose.material:material:1.9.0-alpha04")
    //implementation("androidx.compose.material3:material3:1.4.0-alpha15")

    implementation("androidx.compose.material3:material3:1.3.2") // use latest version
    implementation("androidx.compose.ui:ui:1.6.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.0")
    implementation("androidx.compose.material:material-icons-extended:1.6.0")
    implementation("androidx.activity:activity-compose:1.8.0")

    implementation("androidx.graphics:graphics-shapes:1.0.1")

    // Haze library
    implementation("dev.chrisbanes.haze:haze:1.6.7")
    implementation("dev.chrisbanes.haze:haze-materials:1.6.7")

    //Firebase and Google services dependency
    implementation(platform("com.google.firebase:firebase-bom:33.16.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-firestore:24.4.4")
    implementation("com.google.firebase:firebase-auth:22.3.0")
    implementation("com.google.firebase:firebase-ai")

    // Exoplayer
    implementation("com.google.android.exoplayer:exoplayer:2.19.1")
    implementation("com.google.accompanist:accompanist-insets:0.30.1")
    implementation("com.google.android.exoplayer:exoplayer-core:2.19.1")
    implementation("com.google.android.exoplayer:exoplayer-ui:2.19.1")

    implementation("org.tensorflow:tensorflow-lite:2.17.0")
    implementation("org.tensorflow:tensorflow-lite-gpu:2.17.0")
    implementation("org.tensorflow:tensorflow-lite-select-tf-ops:2.16.1")
}