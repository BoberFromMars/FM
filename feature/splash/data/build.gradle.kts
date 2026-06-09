plugins {
    id("fm.android.library")
    id("fm.android.hilt")
}

android {
    namespace = "com.babrou.fm.feature.splash.data"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":feature:splash:domain"))
}