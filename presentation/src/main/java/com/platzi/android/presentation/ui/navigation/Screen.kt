package com.platzi.android.presentation.ui.navigation

sealed class Screen(val route: String){
    object Home: Screen("home_screen")
    object Saved: Screen("${MainDestinations.SNACK_DETAIL_ROUTE}/{${MainDestinations.SNACK_ID_KEY}}")
}
