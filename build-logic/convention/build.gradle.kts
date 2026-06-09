plugins {
    `kotlin-dsl`
}

group = "com.babrou.fm.convention"

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
    compileOnly(libs.compose.compiler.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "fm.android.application"
            implementationClass = "com.babrou.fm.convention.AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "fm.android.application.compose"
            implementationClass = "com.babrou.fm.convention.AndroidComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "fm.android.library"
            implementationClass = "com.babrou.fm.convention.AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "fm.android.library.compose"
            implementationClass = "com.babrou.fm.convention.AndroidComposeConventionPlugin"
        }
        register("androidHilt") {
            id = "fm.android.hilt"
            implementationClass = "com.babrou.fm.convention.AndroidHiltConventionPlugin"
        }
        register("test") {
            id = "fm.test"
            implementationClass = "com.babrou.fm.convention.TestConventionPlugin"
        }
        register("feature") {
            id = "fm.feature"
            implementationClass = "com.babrou.fm.convention.FeatureConventionPlugin"
        }
    }
}