package com.example.di

import com.example.domain.commons.DomainErrorFactory
import com.example.domain.commons.HandledError
import com.example.domain.repository.DataPagingRepository
import com.example.domain.repository.DataPicRepository
import com.example.domain.repository.PagingRepository
import com.example.domain.repository.PicRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [MyFragmentBuilderModule::class])
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun providesPicRepository(
        dataPicRepository: DataPicRepository
    ) : PicRepository

    @Binds
    abstract fun providesDataPagingRepository(
        dataPagingRepository: DataPagingRepository
    ) : PagingRepository
}

@Module
@InstallIn(SingletonComponent::class)
internal object MyFragmentBuilderModule {
    @Provides
    fun providesDomainErrorFactor() = DomainErrorFactory
}
