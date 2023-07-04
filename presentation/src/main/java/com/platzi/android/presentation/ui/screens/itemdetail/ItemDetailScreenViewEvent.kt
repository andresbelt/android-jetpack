package com.platzi.android.presentation.ui.screens.itemdetail

sealed class ItemDetailScreenViewEvent {
    data class InitEvent(val id: String): ItemDetailScreenViewEvent()
    data class FavClick(val id: String, val fav: Boolean): ItemDetailScreenViewEvent()
}
