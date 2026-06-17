plugins {
    id("fm.android.library")
    id("fm.android.library.compose")
    id("fm.android.hilt")
}

android {
    namespace = "com.babrou.fm.feature.detail.presentation"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":contract"))
    implementation(project(":feature:detail:data"))
    implementation(project(":feature:detail:domain"))
    implementation(project(":feature:detail:navigation"))
    implementation(project(":feature:list:domain"))
    implementation(libs.androidx.compose.material.icons.core.android)
    implementation(libs.androidx.compose.material3)
}