package com.platzi.android.presentation.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.domain.commons.HandledError
import com.platzi.android.presentation.ui.components.TopBar
import com.platzi.android.presentation.ui.components.ListContent
import com.platzi.android.presentation.ui.navigation.MainDestinations
import com.platzi.android.presentation.ui.screens.home.HomeScreenViewEvent.ItemClick
import com.platzi.android.presentation.ui.states.EventEffect
import com.platzi.android.presentation.ui.states.collectAsStateLifecycleAware

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalPagingApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val viewState: HomeScreenViewState by homeViewModel.stateStream.collectAsStateLifecycleAware()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar()
        },
        content = {
            EventEffect(
                event = viewState.downloadFailedEvent,
                onConsumed = homeViewModel::onConsumedDownloadFailedEvent,
            ) {
                if (it is HandledError.ExceptionError){
                    scaffoldState.snackbarHostState.showSnackbar(it.message)
                } else {
                    scaffoldState.snackbarHostState.showSnackbar("Error")
                }
            }
            ListContent(
                items = viewState.photos.collectAsLazyPagingItems(),
                onFavClick = { id, fav ->
                    homeViewModel.uiEvent(ItemClick(id, fav))
                },
                onItemClick = { id ->
                    navController.navigate("${MainDestinations.SNACK_DETAIL_ROUTE}/$id")
                }
            )
        }
    )
}
