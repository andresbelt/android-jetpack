package com.example.data.datasource.remote

import com.example.data.commons.Urls.ListPics
import com.example.data.model.PicImageEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface PicService {
    @GET(ListPics)
    suspend fun getAllImages(
        @Query("page") page: Int
    ): List<PicImageEntity>
}
