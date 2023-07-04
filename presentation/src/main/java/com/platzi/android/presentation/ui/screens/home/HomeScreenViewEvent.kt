package com.platzi.android.presentation.ui.screens.home

sealed class HomeScreenViewEvent {
    object InitEvent: HomeScreenViewEvent()
    data class ItemClick(val id: String, val fav: Boolean): HomeScreenViewEvent()
}
