package com.dmendanyo.mymovies

object Libs {

    const val playServicesLocation = "com.google.android.gms:play-services-location:21.0.1"

    object Kotlin {
        private const val version = "1.6.10"

        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"

        object Coroutines {
            private const val version = "1.6.4"

            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
            const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        }
    }

    object AndroidX {
        private const val core_version = "1.10.1"

        const val androidx_core = "androidx.core:core-ktx:$core_version"

        object Activity {
            private const val version = "1.7.2"

            const val activity = "androidx.activity:activity-compose:$version"
        }

        object Lifecycle {
            private const val version = "2.6.2"

            const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
        }

        object Compose {
            private const val ui_version = "1.4.2"
            private const val material_version = "1.1.1"
            private const val navigation_version = "2.5.3"
            private const val maps_version = "2.11.4"

            const val ui = "androidx.compose.ui:ui:$ui_version"
            const val debug_ui = "androidx.compose.ui:ui-tooling:$ui_version"
            const val ui_tooling = "androidx.compose.ui:ui-tooling-preview:$ui_version"
            const val material3 = "androidx.compose.material3:material3:$material_version"
            const val navigation = "androidx.navigation:navigation-compose:$navigation_version"
            const val maps = "com.google.maps.android:maps-compose:$maps_version"
        }

        object Room {
            private const val version = "2.5.2"

            const val runtime = "androidx.room:room-runtime:$version"
            const val ktx = "androidx.room:room-ktx:$version"
            const val compiler = "androidx.room:room-compiler:$version"
        }
    }

    object Maps {
        private const val version = "18.1.0"

        const val maps = "com.google.android.gms:play-services-maps:$version"
    }

    object JavaX {
        private const val version = "1"
        const val javax = "javax.inject:javax.inject:$version"
    }

    object Hilt {
        private const val version = "2.46.1"
        private const val nav_version = "1.0.0"

        const val android = "com.google.dagger:hilt-android:$version"
        const val navigation = "androidx.hilt:hilt-navigation-compose:$nav_version"
        const val compiler = "com.google.dagger:hilt-android-compiler:$version"
        const val buildGradle = "com.google.dagger.hilt.android:$version"
    }

    object OkHttp3 {
        private const val version = "4.9.3"

        const val loginInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:$version"
    }

    object Retrofit {
        private const val version = "2.9.0"

        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val converterGson = "com.squareup.retrofit2:converter-gson:$version"
    }

    object Coil {
        private const val version = "2.3.0"

        const val coil = "io.coil-kt:coil-compose:$version"
    }

    object Lottie {
        private const val version = "6.1.0"

        const val lottie = "com.airbnb.android:lottie-compose:$version"
    }

    object JUnit {
        private const val version = "4.13.2"

        const val junit = "junit:junit:$version"
    }

    object Mockito {
        private const val version_kotlin = "4.0.0"
        private const val version_inline = "4.4.0"

        const val kotlin = "org.mockito.kotlin:mockito-kotlin:$version_kotlin"
        const val inline = "org.mockito:mockito-inline:$version_inline"
    }

    object Turbine {
        private const val version = "1.0.0"

        const val turbine = "app.cash.turbine:turbine:$version"
    }
}
