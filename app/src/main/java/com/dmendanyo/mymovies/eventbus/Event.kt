package com.dmendanyo.mymovies.eventbus

sealed interface Event {
    data class LoaderEvent(val shouldShowLoader: Boolean) : Event

    data class ErrorEvent(
        val title: String? = null,
        val message: String? = null,
    ) : Event
}

suspend fun showLoader(shouldShowLoader: Boolean) {
    EventBus.publish(Event.LoaderEvent(shouldShowLoader))
}
