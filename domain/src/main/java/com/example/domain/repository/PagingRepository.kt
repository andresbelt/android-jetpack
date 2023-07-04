package com.example.domain.repository

import androidx.paging.PagingData
import com.example.data.model.PicImageEntity
import kotlinx.coroutines.flow.Flow

interface PagingRepository {
    fun getAllImages(): Flow<PagingData<PicImageEntity>>
}
