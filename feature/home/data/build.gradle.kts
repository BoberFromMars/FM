plugins {
    id("fm.android.library")
    id("fm.android.hilt")
}

android {
    namespace = "com.babrou.fm.feature.home.data"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":feature:home:domain"))
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
}