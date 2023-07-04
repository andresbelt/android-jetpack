package com.platzi.android.presentation.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import com.example.domain.commons.HandledError
import com.example.domain.usecase.ChangeFavItemUseCase
import com.example.domain.usecase.GetPhotosListUseCase
import com.platzi.android.presentation.ui.screens.home.HomeScreenViewEvent.InitEvent
import com.platzi.android.presentation.ui.screens.home.HomeScreenViewEvent.ItemClick
import com.platzi.android.presentation.ui.states.consumed
import com.platzi.android.presentation.ui.states.triggered
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@ExperimentalPagingApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPhotosListUseCase: GetPhotosListUseCase,
    private val changeFavUseCase: ChangeFavItemUseCase
) : ViewModel() {

    private val _stateStream = MutableStateFlow(HomeScreenViewState())
    val stateStream = _stateStream.asStateFlow()

    private var state: HomeScreenViewState
        get() = _stateStream.value
        set(newState) {
            _stateStream.update { newState }
        }

    init {
        uiEvent(InitEvent)
    }

    fun uiEvent(event: HomeScreenViewEvent) {
        when (event) {
            is InitEvent -> {
                getPhotosList()
            }

            is ItemClick -> {
                changeFavItem(event)
            }
        }
    }

    private fun onError(error: HandledError) {
        state = state.copy(
            isLoading = false,
            downloadFailedEvent = triggered(error)
        )
    }

    fun onConsumedDownloadFailedEvent() {
        state = state.copy(downloadFailedEvent = consumed())
    }

    private fun changeFavItem(event: ItemClick) {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            changeFavUseCase.execute(ChangeFavItemUseCase.Params(event.id, event.fav))
            .fold( functionLeft = {
                onError(it)
            }, functionRight = {
                state = state.copy(
                    isLoading = false,
                    resultAddFav = triggered(it)
                )
            })
        }
    }

    private fun getPhotosList() {
        state = state.copy(isLoading = true)
        val result = getPhotosListUseCase.execute()
        state = state.copy(
            isLoading = false,
            photos = result,
            downloadSucceededEvent = triggered
        )
    }
}
