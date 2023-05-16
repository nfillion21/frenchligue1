plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "fr.fdj.frenchligue1"
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        applicationId = "fr.fdj.frenchligue1"
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
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
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(Dependencies.coreAndroidX)
    implementation(Dependencies.lifeCycleRuntime)
    implementation(Dependencies.activityCompose)
    implementation(Dependencies.composeNav)
    implementation(platform(Dependencies.composeBom))
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeGraphics)
    implementation(Dependencies.composeUiToolingPreview)
    implementation(Dependencies.composeMaterial3)
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.androidTestJUnit)
    androidTestImplementation(Dependencies.androidTestEspresso)
    androidTestImplementation(platform(Dependencies.androidTestComposeBom))
    androidTestImplementation(Dependencies.androidTestComposeUi)
    debugImplementation(Dependencies.debugComposeUiTooling)
    debugImplementation(Dependencies.debugComposeUiTestManifest)

    // Hilt
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltAndroidCompiler)

    // Coroutines
    implementation(Dependencies.workRuntime)

    // Room
    implementation(Dependencies.roomRuntine)
    implementation(Dependencies.roomKtx)
    annotationProcessor(Dependencies.roomCompiler)
    kapt(Dependencies.roomCompiler)

    // GSon
    implementation(Dependencies.gson)

    // ktor
    implementation(Dependencies.ktor)

    // hilt navigation
    implementation(Dependencies.hiltNavigationCompose)

    // datastore
    implementation(Dependencies.datastorePreferences)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}