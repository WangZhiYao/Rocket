import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)
    id(libs.plugins.hilt.android.plugin.get().pluginId)
    id(libs.plugins.navigation.safe.args.get().pluginId)
}

android {
    namespace = "com.yizhenwind.rocket"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.yizhenwind.rocket"
        minSdk = 26
        targetSdk = 33
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

        debug {
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    applicationVariants.all {
        outputs
            .map { it as BaseVariantOutputImpl }
            .forEach { output ->
                val dateFormat = SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA)
                val outputFileName =
                    "Rocket-$versionName-$name-${dateFormat.format(Date())}.${output.outputFile.extension}"
                output.outputFileName = outputFileName
            }
    }

    signingConfigs {
        getByName("debug") {
            storeFile = file("../Rocket.jks")
            storePassword = "WzY@156987"
            keyAlias = "rocket"
            keyPassword = "WzY@156987"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.androidx.test)

    debugImplementation(libs.leakcanary.android)

    implementation(project(":core:common"))
    implementation(project(":core:framework"))
    implementation(project(":core:mediator"))

    implementation(project(":feature:home"))
    implementation(project(":feature:client"))
    implementation(project(":feature:account"))
    implementation(project(":feature:character"))
    implementation(project(":feature:zoneserver"))
    implementation(project(":feature:sectinternal"))
    implementation(project(":feature:order"))
    implementation(project(":feature:paymentmethod"))
    implementation(project(":feature:category"))
    implementation(project(":feature:subject"))
    implementation(project(":feature:user"))
    implementation(project(":feature:setting"))

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

}