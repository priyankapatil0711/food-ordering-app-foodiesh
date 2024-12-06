plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("kotlin-android")
    id ("dagger.hilt.android.plugin")
}

android {
    namespace = "com.foodordering.foodiesh"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.foodordering.foodiesh"
        minSdk = 26
        targetSdk = 33
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
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    configurations {
        create("cleanedAnnotations")
        implementation {
            exclude(group = "org.jetbrains", module = "annotations")
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Kotlin
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk7:2.0.0")

    // Hilt for DI
    implementation ("com.google.dagger:hilt-android:2.51.1")
    kapt ("com.google.dagger:hilt-compiler:2.51.1") // Hilt annotation processor


    // Retrofit for networking
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // OkHttp for interceptors (authentication)
    implementation ("com.squareup.okhttp3:okhttp:4.12.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Coroutines for background tasks
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")

    // LiveData and ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")

    // Gson for parsing JSON responses
    implementation ("com.google.code.gson:gson:2.10.1")

    // AndroidX libraries
    implementation ("androidx.appcompat:appcompat:1.7.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.2.0")
    implementation ("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.navigation:navigation-runtime-ktx:2.8.4")
    implementation("androidx.room:room-common-jvm:2.7.0-alpha11")
    implementation("androidx.room:room-common:2.6.1")
    implementation("androidx.room:room-compiler:2.6.1")

    // Jetpack Compose
    implementation ("androidx.compose.ui:ui:1.7.5")
    implementation ("androidx.compose.material3:material3:1.3.1")   // Material3 for UI
    implementation ("androidx.compose.ui:ui-tooling-preview:1.7.5")  // Preview support

    // Jetpack Compose and lifecycle
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
    implementation ("androidx.compose.runtime:runtime-livedata:1.7.5")
    implementation ("io.coil-kt:coil-compose:2.7.0")
    implementation ("androidx.compose.material:material:1.7.5")
    implementation ("androidx.navigation:navigation-compose:2.8.4")
    implementation ("androidx.room:room-runtime:2.6.1")
    implementation ("androidx.room:room-ktx:2.6.1")
}