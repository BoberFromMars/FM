plugins {
    id("fm.android.library")
    id("fm.android.hilt")
    id("fm.test")
}

android {
    namespace = "com.babrou.fm.feature.list.domain"
}

dependencies {
    implementation(project(":core"))
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
}