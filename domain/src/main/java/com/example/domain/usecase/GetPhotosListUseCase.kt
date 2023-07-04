package com.example.domain.usecase

import androidx.paging.PagingData
import com.example.data.model.PicImageEntity
import com.example.domain.repository.PagingRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetPhotosListUseCase @Inject constructor(
    private val dataBookRepository: PagingRepository
) {
    fun execute(): Flow<PagingData<PicImageEntity>> {
        return dataBookRepository.getAllImages()
    }
}
