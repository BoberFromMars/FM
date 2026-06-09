plugins {
    id("fm.android.library")
    id("fm.android.hilt")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.babrou.fm.feature.list.navigation"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":contract"))
    implementation(libs.kotlinx.serialization.core)
}
