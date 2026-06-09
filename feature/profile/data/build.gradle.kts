plugins {
    id("fm.android.library")
    id("fm.android.hilt")
}

android {
    namespace = "com.babrou.fm.feature.profile.data"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":feature:profile:domain"))
}