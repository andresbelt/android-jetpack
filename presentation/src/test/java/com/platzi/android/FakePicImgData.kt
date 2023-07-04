package com.platzi.android

import androidx.paging.PagingData
import com.example.data.model.PicImageEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object FakePicImgData {

        fun getFakePagingData():
                Flow<PagingData<PicImageEntity>> {
            return flow {
                emit(PagingData.from(getPicEntity()))
            }
        }

        private fun getPicEntity(): List<PicImageEntity> {
            val gameResults: ArrayList<PicImageEntity> =
                ArrayList()
            gameResults.add(
                PicImageEntity(fav = true)
            )
            gameResults.add(
                PicImageEntity(fav = false)
            )
            return gameResults
        }
    }
