package com.example.domain.repository

import com.example.commons.Either
import com.example.data.error.UserErrorContainer
import com.example.data.model.PicImageEntity

interface PicRepository {
    suspend fun getPicInfo(id: String): Either<UserErrorContainer, PicImageEntity>
    suspend fun changeFavItem(id: String, fav: Boolean): Either<UserErrorContainer, Int>
}
