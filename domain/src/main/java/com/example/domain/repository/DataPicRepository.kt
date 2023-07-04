package com.example.domain.repository

import com.example.commons.Either
import com.example.data.datasource.local.PicImgLocalSource
import com.example.data.error.UserErrorContainer
import com.example.data.model.PicImageEntity
import javax.inject.Inject

class DataPicRepository @Inject constructor(
    private val picImageLocalSource: PicImgLocalSource
) : PicRepository {

    override suspend fun getPicInfo(id: String): Either<UserErrorContainer, PicImageEntity> {
        return picImageLocalSource.getPicInfo(id)
    }

    override suspend fun changeFavItem(id: String, fav: Boolean): Either<UserErrorContainer, Int> {
        return picImageLocalSource.changeFavItem(id, fav)
    }
}
