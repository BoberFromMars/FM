plugins {
    id("fm.android.library")
    id("fm.android.hilt")
    id("fm.test")
}

android {
    namespace = "com.babrou.fm.feature.auth.domain"
}

dependencies {
    implementation(project(":core"))
}