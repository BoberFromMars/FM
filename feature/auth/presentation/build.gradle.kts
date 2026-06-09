plugins {
    id("fm.android.library")
    id("fm.android.library.compose")
    id("fm.android.hilt")
    id("fm.feature")
    id("fm.test")
}

android {
    namespace = "com.babrou.fm.feature.auth.presentation"
}

dependencies {
    implementation(project(":feature:auth:domain"))
    implementation(project(":feature:auth:navigation"))
    implementation(project(":feature:splash:navigation"))
    implementation(project(":feature:home:navigation"))
}