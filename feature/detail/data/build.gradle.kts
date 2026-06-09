plugins {
    id("fm.android.library")
    id("fm.android.hilt")
}

android {
    namespace = "com.babrou.fm.feature.detail.data"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":feature:detail:domain"))
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
}