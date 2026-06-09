plugins {
    id("fm.android.library")
    id("fm.android.hilt")
    id("fm.test")
}

android {
    namespace = "com.babrou.fm.feature.auth.data"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":feature:auth:domain"))
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

}