package com.example.di

import android.content.Context
import androidx.room.Room
import com.example.data.commons.Constants.UNSPLASH_DATABASE
import com.example.data.datasource.local.PicImageDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): PicImageDatabase {
        return Room.databaseBuilder(
            context,
            PicImageDatabase::class.java,
            UNSPLASH_DATABASE
        ).build()
    }
}
