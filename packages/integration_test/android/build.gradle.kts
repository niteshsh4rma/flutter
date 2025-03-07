// Copyright 2014 The Flutter Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

group = "dev.flutter.plugins.integration_test"
version = "1.0-SNAPSHOT"

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.3.1")
    }
}

plugins {
    id("com.android.library")
}

rootProject.allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

android {
    // Conditional for compatibility with AGP <4.2.
    if (project.findProject("android")?.hasProperty("namespace") == true) {
        namespace = "dev.flutter.integration_test"
    }

    namespace = "dev.flutter.integration_test"
    compileSdk = 34

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    defaultConfig {
        minSdk = 21
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("lib-proguard-rules.txt")
    }

    dependencies {
        // TODO(egarciad): These dependencies should not be added to release builds.
        // https://github.com/flutter/flutter/issues/56591
        testImplementation("junit:junit:4.12")
        testImplementation("org.mockito:mockito-inline:5.0.0")

        // https://developer.android.com/jetpack/androidx/releases/test/#1.2.0
        api("androidx.test:runner:1.2.0")
        api("androidx.test:rules:1.2.0")
        api("androidx.test.espresso:espresso-core:3.2.0")

        implementation("com.google.guava:guava:28.1-android")
    }
}
