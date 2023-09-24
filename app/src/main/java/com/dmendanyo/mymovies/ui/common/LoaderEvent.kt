package com.dmendanyo.mymovies.ui.common

import com.dmendanyo.mymovies.eventbus.Event

data class LoaderEvent(val shouldShowLoader: Boolean) : Event
