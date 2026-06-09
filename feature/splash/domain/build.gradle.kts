plugins {
    id("fm.android.library")
    id("fm.android.hilt")
}

android {
    namespace = "com.babrou.fm.feature.splash.domain"
}

dependencies {
    implementation(project(":core"))
}