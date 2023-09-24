package com.dmendanyo.mymovies.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.dmendanyo.mymovies.extensions.subscribeLoader
import com.dmendanyo.mymovies.ui.common.LoaderComponent
import com.dmendanyo.mymovies.ui.theme.MyMoviesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val shouldShowLoader = mutableStateOf(true)
        lifecycleScope.subscribeLoader { shouldShowLoader.value = it }

        setContent {
            MyMoviesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val showLoader by remember { shouldShowLoader }
                    Box {
                        MainScreen()
                        if (showLoader) {
                            LoaderComponent()
                        }
                    }
                }
            }
        }
    }
}
