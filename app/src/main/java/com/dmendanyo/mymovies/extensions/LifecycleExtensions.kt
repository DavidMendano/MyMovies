package com.dmendanyo.mymovies.extensions

import androidx.lifecycle.LifecycleCoroutineScope
import com.dmendanyo.mymovies.eventbus.EventBus
import com.dmendanyo.mymovies.ui.common.LoaderEvent
import kotlinx.coroutines.launch

fun LifecycleCoroutineScope.subscribeLoader(onEvent: (Boolean) -> Unit) {
    this.launch {
        EventBus.subscribe<LoaderEvent> { onEvent(it.shouldShowLoader) }
    }
}
