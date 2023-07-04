package com.platzi.android.presentation.ui.screens.home

import androidx.paging.PagingData
import com.example.data.model.PicImageEntity
import com.example.domain.commons.HandledError
import com.platzi.android.presentation.ui.states.StateEvent
import com.platzi.android.presentation.ui.states.StateEventWithContent
import com.platzi.android.presentation.ui.states.consumed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeScreenViewState(
    val isLoading: Boolean = false,
    val photos: Flow<PagingData<PicImageEntity>> = emptyFlow(),
    val downloadSucceededEvent: StateEvent = consumed,
    val downloadFailedEvent: StateEventWithContent<HandledError> = consumed(),
    val resultAddFav: StateEventWithContent<Int> = consumed()
)
