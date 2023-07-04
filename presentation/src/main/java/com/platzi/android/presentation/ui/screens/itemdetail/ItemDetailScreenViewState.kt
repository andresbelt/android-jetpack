package com.platzi.android.presentation.ui.screens.itemdetail

import com.example.data.model.PicImageEntity
import com.example.domain.commons.HandledError
import com.platzi.android.presentation.ui.states.StateEventWithContent
import com.platzi.android.presentation.ui.states.consumed

data class ItemDetailScreenViewState(
    val isLoading: Boolean = false,
    val picInfo: PicImageEntity = PicImageEntity(fav = false),
    val resultAddFav: StateEventWithContent<Int> = consumed(),
    val error: StateEventWithContent<HandledError> = consumed()
)
