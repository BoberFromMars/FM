plugins {
    id("fm.android.library")
    id("fm.android.library.compose")
    id("fm.android.hilt")
}

android {
    namespace = "com.babrou.fm.feature.search.presentation"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":contract"))
    implementation(project(":feature:search:domain"))
    implementation(project(":feature:search:navigation"))
    implementation(libs.androidx.appcompat)
}
