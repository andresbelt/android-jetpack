package com.example.data.datasource.local

import androidx.paging.PagingSource
import com.example.commons.Either
import com.example.data.error.GenericUserErrorContainer
import com.example.data.error.UserError.Type.EXCEPTION
import com.example.data.error.UserErrorContainer
import com.example.data.model.PicImageEntity
import javax.inject.Inject

class PicImgLocalDataSource @Inject constructor(
    private val database: PicImageDatabase
) : PicImgLocalSource {

    override fun getAllImages(): PagingSource<Int, PicImageEntity> {
        return database.unsplashImageDao().getAllPic()
    }

    override suspend fun changeFavItem(id: String, fav: Boolean): Either<UserErrorContainer, Int> {
        return try {
            Either.Right(database.unsplashImageDao().changeFavItem(id, fav))
        } catch (exception: Exception) {
            Either.Left(GenericUserErrorContainer(EXCEPTION, exception.message))
        }
    }

    override suspend fun getPicInfo(id: String): Either<UserErrorContainer, PicImageEntity> {
        return try {
            Either.Right(database.unsplashImageDao().getPicInfo(id))
        } catch (exception: Exception) {
            Either.Left(GenericUserErrorContainer(EXCEPTION, exception.message))
        }
    }
}

