package com.platzi.android.presentation.ui.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.ExperimentalPagingApi
import com.platzi.android.presentation.ui.screens.home.HomeScreen
import com.platzi.android.presentation.ui.screens.itemdetail.ItemDetailScreen

@ExperimentalPagingApi
@Composable
fun SetupNavGraph(navController: NavHostController, scaffoldState: ScaffoldState) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController, scaffoldState = scaffoldState)
        }
        composable(route = Screen.Saved.route,
            arguments = listOf(navArgument(MainDestinations.SNACK_ID_KEY) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val snackId = arguments.getString(MainDestinations.SNACK_ID_KEY) ?: "0"
            ItemDetailScreen(snackId, navController = navController, scaffoldState = scaffoldState)
        }
    }
}


object MainDestinations {
    const val SNACK_DETAIL_ROUTE = "snack"
    const val SNACK_ID_KEY = "snackId"
}
