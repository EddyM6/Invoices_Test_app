plugins {
    id("com.android.library")
    id(Dependencies.Plugins.kotlinPlugin)
    id(Dependencies.Plugins.kotlinKapt)
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    sourceSets.all {
        java.srcDir("src/$name/kotlin")
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.composeVersion
    }
}

dependencies {

    implementation(Dependencies.Android.ktxCore)
    implementation(Dependencies.Android.runtimeLifecycle)
    testImplementation(Dependencies.Android.jUnit)
    implementation(Dependencies.Android.jUnit)
    androidTestImplementation(Dependencies.Android.jUnitTest)
    androidTestImplementation(Dependencies.Android.espresso)

    // Koin main features for Android
    implementation(Dependencies.Koin.koinAndroid)
    implementation(Dependencies.Koin.koinCompose)
    implementation(Dependencies.Koin.koinCore)

    // Coroutines
    implementation(Dependencies.Coroutines.coroutinesAndroid)
    implementation(Dependencies.Coroutines.coroutinesCore)

    implementation(Dependencies.Coroutines.TEST)

}