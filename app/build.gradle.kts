plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    alias(libs.plugins.kotlin.ksp) // <-- ACTIVA EL PLUGIN AQUÍ
}

android {
    namespace = "com.example.appcalculadoraagua"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.appcalculadoraagua"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // Implementación de Room usando el archivo TOML
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)

    // ESTA ES LA LÍNEA QUE DABA ERROR, ahora usa 'libs' y funcionará:
    ksp(libs.room.compiler)
//la dependencia de activity-compose solucionó el problema. Esa librería
// es el "pegamento" entre la actividad tradicional y el mundo de Compose.
    implementation("androidx.activity:activity-compose:1.9.2")
}