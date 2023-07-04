package com.platzi.android.presentation.ui.screens.itemdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.commons.HandledError
import com.example.domain.usecase.ChangeFavItemUseCase
import com.example.domain.usecase.GetInfoPicUseCase
import com.example.domain.usecase.GetInfoPicUseCase.Params
import com.platzi.android.presentation.ui.screens.itemdetail.ItemDetailScreenViewEvent.FavClick
import com.platzi.android.presentation.ui.screens.itemdetail.ItemDetailScreenViewEvent.InitEvent
import com.platzi.android.presentation.ui.states.triggered
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ItemDetailModel @Inject constructor(
    private val getPicInfoUseCase: GetInfoPicUseCase,
    private val changeFavUseCase: ChangeFavItemUseCase,
    ): ViewModel() {

    private val _stateStream = MutableStateFlow(ItemDetailScreenViewState())
    val stateStream = _stateStream.asStateFlow()

    private var state: ItemDetailScreenViewState
        get() = _stateStream.value
        set(newState) {
            _stateStream.update { newState }
        }

    fun uiEvent(event: ItemDetailScreenViewEvent) {
        when(event) {
            is InitEvent -> {
                getInfoPic(event.id)
            }
            is FavClick -> {
               changeFavItem(event)
            }
        }
    }

    private fun onError(error: HandledError) {
        state = state.copy(
            isLoading = false,
            error = triggered(error)
        )
    }

    private fun changeFavItem(event: FavClick) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = changeFavUseCase.execute(ChangeFavItemUseCase.Params(event.id, event.fav))
            result.fold( functionLeft = {
               onError(it)
            }, functionRight = {
                state = state.copy(
                    isLoading = false,
                    resultAddFav = triggered(it)
                )
            })
        }
    }

    private fun getInfoPic(id: String) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            getPicInfoUseCase.execute(Params(id)).fold({
                onError(it)
            }, {
                state = state.copy(
                    isLoading = false,
                    picInfo = it
                )
            })
        }
    }
}
