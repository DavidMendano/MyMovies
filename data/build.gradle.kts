import com.dmendanyo.mymovies.Libs

plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(project(mapOf("path" to ":domain")))

    implementation(com.dmendanyo.mymovies.Libs.Kotlin.Coroutines.core)
    implementation(com.dmendanyo.mymovies.Libs.JavaX.javax)
}
