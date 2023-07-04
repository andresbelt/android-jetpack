package com.example.data.datasource.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.PicImageEntity

@Dao
interface PicSumImageDao {
    @Query("SELECT * FROM pic_image_table")
    fun getAllPic(): PagingSource<Int, PicImageEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPics(images: List<PicImageEntity>)

    @Query("UPDATE pic_image_table SET fav = :fav WHERE id = :id")
    suspend fun changeFavItem(id: String, fav: Boolean): Int

    @Query("SELECT * FROM pic_image_table WHERE id = :id")
    suspend fun getPicInfo(id: String): PicImageEntity
}
