plugins {
    id("fm.android.library")
    id("fm.android.library.compose")
    id("fm.android.hilt")
}

android {
    namespace = "com.babrou.fm.feature.profile.presentation"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":contract"))
    implementation(project(":feature:profile:domain"))
    implementation(project(":feature:profile:navigation"))
    implementation(libs.androidx.compose.material.icons.core.android)
}