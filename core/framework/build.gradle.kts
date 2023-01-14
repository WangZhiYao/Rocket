@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)
    id(libs.plugins.hilt.android.plugin.get().pluginId)
}

android {
    namespace = "com.yizhenwind.rocket.core.framework"
    compileSdk = 33

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    viewBinding {
        enable = true
    }
}

dependencies {

    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.androidx.test)

    implementation(project(":core:common"))
    implementation(project(":core:logger"))

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    implementation(libs.androidx.startup)

    api(libs.bundles.kotlinx.coroutines)

    api(libs.androidx.core.ktx)
    api(libs.androidx.appcompat)
    api(libs.google.material)

    api(libs.androidx.activity.ktx)
    api(libs.androidx.fragment.ktx)

    api(libs.bundles.androidx.navigation)

    api(libs.bundles.androidx.lifecycle)

    api(libs.bundles.orbit.mvi)

    api(libs.androidx.paging.runtime.ktx)

}