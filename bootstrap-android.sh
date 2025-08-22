#!/usr/bin/env bash
# bootstrap-android.sh
# One-shot script to scaffold your Android project (Gradle + app/)

set -euo pipefail

ROOT_DIR="$(pwd)"
APP_DIR="$ROOT_DIR/app"
SRC_DIR="$APP_DIR/src/main/java/com/example/simonfxx"
RES_DIR="$APP_DIR/src/main/res/layout"
WF_DIR="$ROOT_DIR/.github/workflows"

echo ">> Creating folders"
mkdir -p "$SRC_DIR"
mkdir -p "$RES_DIR"
mkdir -p "$WF_DIR"

# --- gradle.properties ---
cat > "$ROOT_DIR/gradle.properties" <<'EOF'
org.gradle.jvmargs=-Xmx2g -Dfile.encoding=UTF-8
android.useAndroidX=true
kotlin.code.style=official
kotlin.build.report.enable=false
EOF

# --- settings.gradle.kts ---
cat > "$ROOT_DIR/settings.gradle.kts" <<'EOF'
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "simonfxx"
include(":app")
EOF

# --- Root build.gradle.kts ---
cat > "$ROOT_DIR/build.gradle.kts" <<'EOF'
plugins {
    id("com.android.application") version "8.5.2" apply false
    kotlin("android") version "1.9.22" apply false
}
EOF

# --- app/build.gradle.kts ---
cat > "$APP_DIR/build.gradle.kts" <<'EOF'
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

@Suppress("UNCHECKED_CAST")
val geminiKey: String = (project.findProperty("GEMINI_API_KEY") as String?) ?: ""

android {
    namespace = "com.example.simonfxx"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.simonfxx"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

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

    buildFeatures { viewBinding = true }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }

    packaging {
        resources { excludes += "/META-INF/{AL2.0,LGPL2.1}" }
    }
}

dependencies {
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
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation(platform("com.google.firebase:firebase-bom:33.6.0"))
}
EOF

# --- Manifest ---
cat > "$APP_DIR/src/main/AndroidManifest.xml" <<'EOF'
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    <application
        android:name=".App"
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="simonfxx"
        android:supportsRtl="true"
        android:theme="@style/Theme.Material3.DayNight.NoActionBar">
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN"/>
                <category android:name="android.intent.category.LAUNCHER"/>
            </intent-filter>
        </activity>
    </application>
</manifest>
EOF

# --- App.kt ---
cat > "$SRC_DIR/App.kt" <<'EOF'
package com.example.simonfxx

import android.app.Application

class App : Application()
EOF

# --- MainActivity.kt ---
cat > "$SRC_DIR/MainActivity.kt" <<'EOF'
package com.example.simonfxx

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.simonfxx.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val key = BuildConfig.GEMINI_API_KEY
        binding.hello.text = if (key.isBlank()) {
            "Hello, SimonFXX!\n(GEMINI_API_KEY not set)"
        } else {
            "Hello, SimonFXX!\nAPI key present ✓"
        }
    }
}
EOF

# --- Layout ---
cat > "$RES_DIR/activity_main.xml" <<'EOF'
<?xml version="1.0" encoding="utf-8"?>
<androidx.coordinatorlayout.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/hello"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Building…"
        android:textSize="20sp"
        android:layout_gravity="center"/>
</androidx.coordinatorlayout.widget.CoordinatorLayout>
EOF

# --- proguard-rules.pro ---
cat > "$APP_DIR/proguard-rules.pro" <<'EOF'
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable
EOF

# --- Workflow ---
cat > "$WF_DIR/android.yml" <<'EOF'
name: Android CI

on:
  push:
    branches: ["**"]
  pull_request:
    branches: ["**"]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: "17"
      - uses: gradle/actions/setup-gradle@v3
      - run: chmod +x ./gradlew
      - run: ./gradlew clean --stacktrace --no-daemon
      - name: Build APK
        env:
          GEMINI_API_KEY: ${{ secrets.GEMINI_API_KEY }}
        run: ./gradlew assembleDebug --stacktrace --no-daemon -PGEMINI_API_KEY="${GEMINI_API_KEY}"
      - uses: actions/upload-artifact@v4
        with:
          name: app-debug
          path: app/build/outputs/apk/debug/*.apk
EOF

echo "✅ Android project skeleton created under: $ROOT_DIR"