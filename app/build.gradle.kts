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
    implementation(libs.androidx.appcompat)

    implementation(libs.material)

    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)

    implementation(libs.androidx.material3)

    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("androidx.viewpager:viewpager:1.0.0")
    implementation("com.google.android.material:material:1.4.0")

    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("com.github.bumptech.glide:compose:1.0.0-beta01")

    implementation("com.clevertap.android:clevertap-android-sdk:6.1.1")

    implementation("androidx.navigation:navigation-compose:2.7.5")

    implementation("androidx.compose.material3:material3:1.2.0-alpha04")

    implementation("com.google.dagger:hilt-android:2.48")
    implementation(libs.firebase.messaging.ktx)
    ksp("com.google.dagger:hilt-compiler:2.48")

    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
//    implementation("androidx.ui:ui-tooling:$compose_version")
//    implementation("androidx.compose.runtime:runtime:$compose_version")
//    implementation("androidx.compose.compiler:compiler:$compose_version")

    // Add the dependencies for any other desired Firebase products
    // https://firebase.google.com/docs/android/setup#available-libraries

    //annotationProcessor()

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
//        val lifecycle_version = "2.7.0"
//        val arch_version = "2.2.0"
//
//        // ViewModel
//        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
//        // ViewModel utilities for Compose
//        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
//        // LiveData
//        implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
//        // Lifecycles only (without ViewModel or LiveData)
//        implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")
//        // Lifecycle utilities for Compose
//        implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycle_version")
//
//        // Saved state module for ViewModel
//        implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version")
//
//        // Annotation processor
//        kapt("androidx.lifecycle:lifecycle-compiler:$lifecycle_version")
//        // alternately - if using Java8, use the following instead of lifecycle-compiler
//        implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycle_version")
//
//        // optional - helpers for implementing LifecycleOwner in a Service
//        implementation("androidx.lifecycle:lifecycle-service:$lifecycle_version")
//
//        // optional - ProcessLifecycleOwner provides a lifecycle for the whole application process
//        implementation("androidx.lifecycle:lifecycle-process:$lifecycle_version")
//
//        // optional - ReactiveStreams support for LiveData
//        implementation("androidx.lifecycle:lifecycle-reactivestreams-ktx:$lifecycle_version")
//
//        // optional - Test helpers for LiveData
//        testImplementation("androidx.arch.core:core-testing:$arch_version")
//
//        // optional - Test helpers for Lifecycle runtime
//        testImplementation ("androidx.lifecycle:lifecycle-runtime-testing:$lifecycle_version")

}