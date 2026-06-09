plugins {
    id("fm.android.library")
    id("fm.android.hilt")
}

android {
    namespace = "com.babrou.fm.feature.search.data"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":feature:search:domain"))
}
