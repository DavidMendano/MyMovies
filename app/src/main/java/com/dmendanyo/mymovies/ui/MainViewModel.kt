package com.dmendanyo.mymovies.ui

import androidx.lifecycle.ViewModel
import com.dmendanyo.mymovies.navigation.BottomNavigationScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _currentTab = MutableStateFlow(BottomNavigationScreen.Home.route)
    val currentTab: StateFlow<String> = _currentTab

    fun setCurrentRoute(route: String) {
        this._currentTab.value = route
    }
}
