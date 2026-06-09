plugins {
    id("fm.android.library")
    id("fm.android.library.compose")
    id("fm.android.hilt")
    id("fm.feature")
}

android {
    namespace = "com.babrou.fm.feature.home.presentation"
}

dependencies {
    implementation(project(":feature:home:domain"))
    implementation(project(":feature:home:data"))
    implementation(project(":feature:home:navigation"))
    implementation(project(":feature:list:navigation"))
}