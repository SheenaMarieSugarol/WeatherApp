plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.weatherapp"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.weatherapp"
        minSdk = 33
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")

// Moshi Converter for Retrofit
    implementation("com.squareup.retrofit2:converter-moshi:2.11.0")

// Moshi Kotlin
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")

// Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
}