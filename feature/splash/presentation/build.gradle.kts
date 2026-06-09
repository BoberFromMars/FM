plugins {
    id("fm.android.library")
    id("fm.android.library.compose")
    id("fm.android.hilt")
}

android {
    namespace = "com.babrou.fm.feature.splash.presentation"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":contract"))
    implementation(project(":feature:splash:domain"))
    implementation(project(":feature:splash:navigation"))
    implementation(project(":feature:home:navigation"))
    implementation(project(":feature:auth:navigation"))

}