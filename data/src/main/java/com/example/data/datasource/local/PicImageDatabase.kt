package com.example.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.datasource.local.dao.PicSumImageDao
import com.example.data.datasource.local.dao.PicSumImageRemoteKeysDao
import com.example.data.model.PicImageEntity
import com.example.data.model.PicSumImageRemoteKeys

@Database(entities = [PicImageEntity::class, PicSumImageRemoteKeys::class], version = 1)
abstract class PicImageDatabase : RoomDatabase() {
    abstract fun unsplashImageDao(): PicSumImageDao
    abstract fun unsplashRemoteKeysDao(): PicSumImageRemoteKeysDao
}
