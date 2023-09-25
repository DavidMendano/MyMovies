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
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.lifecycleScope
import com.dmendanyo.mymovies.R
import com.dmendanyo.mymovies.eventbus.Event
import com.dmendanyo.mymovies.extensions.subscribeError
import com.dmendanyo.mymovies.extensions.subscribeLoader
import com.dmendanyo.mymovies.ui.common.ErrorBottomSheet
import com.dmendanyo.mymovies.ui.common.LoaderComponent
import com.dmendanyo.mymovies.ui.theme.MyMoviesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val shouldShowLoader = mutableStateOf(true)
        lifecycleScope.subscribeLoader { shouldShowLoader.value = it }

        val error = mutableStateOf<Event.ErrorEvent?>(null)
        lifecycleScope.subscribeError { error.value = it }

        setContent {
            if (error.value != null) {
                ErrorBottomSheet(
                    title = error.value?.title ?: stringResource(id = R.string.error),
                    subtitle = error.value?.message ?: stringResource(id = R.string.error),
                    onAccept = { error.value = null },
                    onDismiss = { error.value = null },
                )
            }
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
