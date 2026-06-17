plugins {
    id("fm.android.library")
    id("fm.android.hilt")
}

android {
    namespace = "com.babrou.fm.feature.detail.domain"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":feature:list:domain"))
}