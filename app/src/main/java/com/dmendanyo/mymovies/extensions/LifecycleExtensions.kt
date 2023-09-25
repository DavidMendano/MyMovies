package com.dmendanyo.mymovies.extensions

import androidx.lifecycle.LifecycleCoroutineScope
import com.dmendanyo.domain.models.Error
import com.dmendanyo.mymovies.eventbus.Event
import com.dmendanyo.mymovies.eventbus.EventBus
import kotlinx.coroutines.launch

fun LifecycleCoroutineScope.subscribeLoader(onEvent: (Boolean) -> Unit) {
    this.launch {
        EventBus.subscribe<Event.LoaderEvent> { onEvent(it.shouldShowLoader) }
    }
}

fun LifecycleCoroutineScope.subscribeError(onEvent: (Event.ErrorEvent?) -> Unit) {
    this.launch {
        EventBus.subscribe<Event.ErrorEvent> { onEvent(it) }
    }
}

suspend fun Error.throwError(message: String?) {
    EventBus.publish(Event.ErrorEvent(message))
}
