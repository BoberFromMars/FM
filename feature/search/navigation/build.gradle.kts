plugins {
    id("fm.android.library")
    id("fm.android.hilt")
    id("fm.android.library.compose")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.babrou.fm.feature.search.navigation"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":contract"))
    implementation(libs.kotlinx.serialization.core)
    implementation(libs.androidx.compose.material.icons.core.android)
}

