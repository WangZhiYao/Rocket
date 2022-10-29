// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.hilt.android) apply false
}

buildscript {
    dependencies {
        classpath(libs.android.tools.build.gradle)
        classpath(libs.kotlin.gradle.plugin)
    }
}

allprojects {
    subprojects {
        project.configurations.all {
            resolutionStrategy.eachDependency {
                /*when (requested.group) {
                    "androidx.activity" -> useVersion("1.6.0")
                    "androidx.fragment" -> useVersion("1.5.3")
                    "androidx.annotation" -> useVersion("1.3.0")
                    "androidx.collection" -> useVersion("1.2.0")
                    "androidx.core" -> useVersion("1.9.0")
                    "androidx.customview" -> useVersion("1.1.0")
                    "androidx.lifecycle" -> useVersion("2.5.1")
                    "com.google.code.findbugs" -> useVersion("3.0.2")
                    "org.jetbrains.kotlin" -> useVersion("1.7.20")
                    "org.jetbrains.kotlinx" -> useVersion("1.6.4")
                }*/
            }
        }
    }
}