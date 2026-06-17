plugins {
    id("fm.android.library")
    id("fm.android.library.compose")
    id("fm.android.hilt")
    id("fm.feature")
    id("fm.test")
}

android {
    namespace = "com.babrou.fm.feature.list.presentation"
}

dependencies {
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material.icons.core.android)
    implementation(project(":feature:list:data"))
    implementation(project(":feature:list:domain"))
    implementation(project(":feature:list:navigation"))
    implementation(project(":feature:detail:navigation"))
}