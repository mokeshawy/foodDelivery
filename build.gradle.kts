// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    val kotlinVersion  = "2.1.0"
    id("com.android.application") version "8.11.1" apply false
    id("org.jetbrains.kotlin.android") version kotlinVersion apply false
    id("org.jetbrains.kotlin.plugin.serialization") version kotlinVersion apply false
    id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false
    id("com.google.dagger.hilt.android")  version "2.57.1" apply false
    id("com.google.gms.google-services") version "4.4.3" apply false
    id("com.google.firebase.crashlytics") version "3.0.4" apply false
    id("org.jetbrains.kotlin.plugin.compose") version kotlinVersion apply false
    id("com.google.protobuf") version "0.9.5" apply false
    id("com.android.library") version "8.11.1" apply false
}