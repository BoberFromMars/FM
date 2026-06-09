plugins {
    id("fm.android.library")
    id("fm.android.hilt")
    id("fm.test")
}

android {
    namespace = "com.babrou.fm.feature.list.data"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":feature:list:domain"))
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
}