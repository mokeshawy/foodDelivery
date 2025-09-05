plugins {
    id("com.google.devtools.ksp")
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("org.jetbrains.kotlin.plugin.compose")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("kotlin-parcelize")
    id("com.google.protobuf")
}

android {
    namespace = "com.saham.fooddelivery"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.saham.fooddelivery"

        testInstrumentationRunner = "com.saham.hilt_test_runner.HiltTestRunner"
        minSdk = 24
        targetSdk = 36
        versionCode = 2
        versionName = "1.3.0"

    }

    buildTypes {
        getByName("release") {
            /* signingConfig = signingConfigs.getByName("release") */
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions.add("version")
    productFlavors {
        create("dev") {
            dimension = "version"
            versionNameSuffix = ".dev"
            applicationIdSuffix = ".dev"
        }

        create("live") {
            dimension = "version"
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

    implementation(project(path = ":core"))
    implementation(project(path = ":networking"))
    implementation(project(path = ":ui_component"))
    implementation(project(path = ":websocket"))


    // Testing Dependencies
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    testImplementation("junit:junit:4.13.2")

    testImplementation("org.mockito:mockito-core:5.19.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:6.0.0")

    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.2")

    androidTestImplementation("com.google.dagger:hilt-android-testing:2.57.1")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.57.1")
    testImplementation("com.google.dagger:hilt-android-testing:2.57.1")
    kaptTest("com.google.dagger:hilt-android-compiler:2.57.1")

    // AndroidX Test
    androidTestImplementation("androidx.test:core:1.7.0")
    androidTestImplementation("androidx.test:runner:1.7.0")
    androidTestImplementation("androidx.test:rules:1.7.0")

    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")

    androidTestImplementation("androidx.arch.core:core-testing:2.2.0")

    androidTestImplementation("app.cash.turbine:turbine:1.2.1")

    testImplementation("app.cash.turbine:turbine:1.2.1")



    // Hilt Testing
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.57.1")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.57.1")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.57.1")
    kapt("com.google.dagger:hilt-compiler:2.57.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    kapt("androidx.hilt:hilt-compiler:1.2.0")


    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.5")
}