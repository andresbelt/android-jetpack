package com.example.data.datasource.local

import androidx.paging.PagingSource
import com.example.commons.Either
import com.example.data.error.UserErrorContainer
import com.example.data.model.PicImageEntity

interface PicImgLocalSource {
    fun getAllImages(): PagingSource<Int, PicImageEntity>
    suspend fun changeFavItem(id: String, fav: Boolean): Either<UserErrorContainer, Int>
    suspend fun getPicInfo(id: String): Either<UserErrorContainer, PicImageEntity>
}
