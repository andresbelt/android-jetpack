package com.example.domain.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.commons.Constants.ITEMS_PER_PAGE
import com.example.data.datasource.local.PicImgLocalSource
import com.example.data.model.PicImageEntity
import com.example.data.paging.UnsplashRemoteMediator
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalPagingApi::class)
class DataPagingRepository @Inject constructor(
    private val picImageLocalSource: PicImgLocalSource,
    private val unsplashRemoteMediator: UnsplashRemoteMediator,
) : PagingRepository {

    @ExperimentalPagingApi
    override fun getAllImages(): Flow<PagingData<PicImageEntity>> {
        val pagingSourceFactory = { picImageLocalSource.getAllImages() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = unsplashRemoteMediator,
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}
