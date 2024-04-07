plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    // ...

    // Add the dependency for the Google services Gradle plugin
    id("com.google.gms.google-services")
    //kotlin("kapt")
    id("com.google.dagger.hilt.android")
    //id("kotlin-kapt")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.kosmasfn.cttest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.kosmasfn.cttest"
        minSdk = 24
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
//    kapt {
//        correctErrorTypes = true
//        arguments {
//            arg("key", "value")
//        }
//        useBuildCache = false
//        tasks.withType<org.jetbrains.kotlin.gradle.internal.KaptWithoutKotlincTask>()
//            .configureEach {
//                kaptProcessJvmArgs.add("-Xmx512m")
//            }
//        showProcessorStats = true
//        javacOptions {
//            // Increase the max count of errors from annotation processors.
//            // Default is 100.
//            option("-Xmaxerrs", 500)
//        }
//        keepJavacAnnotationProcessors = true
//        generateStubs = true
//    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.viewpager)
    implementation(libs.material.v140)

    implementation(libs.material)

    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.glide)
    implementation(libs.compose)

    implementation(libs.clevertap.android.sdk.v611)

    implementation(libs.firebase.messaging.ktx)

    implementation(libs.androidx.navigation.compose)

    implementation(libs.material3)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.converter.gson)
    implementation(libs.retrofit)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}