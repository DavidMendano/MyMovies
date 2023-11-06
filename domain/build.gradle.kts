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
    implementation(Libs.Kotlin.Coroutines.core)
    implementation(Libs.Retrofit.converterGson)
    implementation(Libs.JavaX.javax)

    testImplementation(Libs.JUnit.junit)
    testImplementation(Libs.Mockito.kotlin)
    testImplementation(Libs.Mockito.inline)
}
