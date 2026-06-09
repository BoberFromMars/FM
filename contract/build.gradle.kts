plugins {
    id("fm.android.library")
    id("fm.android.library.compose")
}

android {
    namespace = "com.babrou.fm.contract"
}

dependencies {
    implementation(project(":core"))
    implementation(libs.androidx.core)
    implementation(libs.androidx.compose.bom)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.tooling.preview)
    api(libs.androidx.navigation3.runtime)
    implementation(libs.kotlinx.serialization.core)
    implementation(libs.androidx.compose.material.icons.core.android)
}