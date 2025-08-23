plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

// Read local.properties safely (for GEMINI_API_KEY, sdk.dir, etc.)
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

android {
    namespace = "com.example.simonfxx"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.simonfxx"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        // Read Gemini API key from local.properties and expose as BuildConfig.GEMINI_API_KEY
        val geminiKey: String = gradleLocalProperties(rootDir).getProperty("GEMINI_API_KEY") ?: ""
        buildConfigField("String", "GEMINI_API_KEY", "\"$geminiKey\"")

        vectorDrawables { useSupportLibrary = true }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        // Important: needed for custom BuildConfig fields
        buildConfig = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // AndroidX / Material
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.fragment:fragment-ktx:1.8.3")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation("androidx.preference:preference-ktx:1.2.1")
    implementation("androidx.work:work-runtime-ktx:2.9.1")
    implementation("androidx.coordinatorlayout:coordinatorlayout:1.2.0")

    // Networking
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    // (Optional) Firebase BOM if you use Firebase later
    implementation(platform("com.google.firebase:firebase-bom:33.6.0"))
}
