plugins {
    id("com.android.library")
    id(Dependencies.Plugins.kotlinPlugin)
    id(Dependencies.Plugins.kotlinKapt)
    id(Dependencies.Plugins.kotlinParcelize)
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
            isMinifyEnabled = true
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://storage.googleapis.com/\""
            )
        }
        debug {
            isMinifyEnabled = false
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://storage.googleapis.com/\""
            )
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
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":core"))

    // Koin main features for Android
    implementation(Dependencies.Koin.koinAndroid)
    implementation(Dependencies.Koin.koinCompose)
    implementation(Dependencies.Koin.koinCore)

    // Coroutines
    implementation(Dependencies.Coroutines.coroutinesAndroid)
    implementation(Dependencies.Coroutines.coroutinesCore)

    // Retrofit
    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.gsonConverter)
    implementation(Dependencies.Retrofit.adapterRxjava3)

    // okHttpClient
    implementation(Dependencies.OkhttpClient.okhttpClient)
    implementation(Dependencies.OkhttpClient.loggingInterceptor)
}