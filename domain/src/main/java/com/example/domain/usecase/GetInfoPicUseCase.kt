package com.example.domain.usecase

import com.example.commons.Either
import com.example.commons.SuspendableResultUseCase
import com.example.data.model.PicImageEntity
import com.example.domain.commons.DomainErrorFactory
import com.example.domain.commons.HandledError
import com.example.domain.repository.PicRepository
import com.example.domain.usecase.GetInfoPicUseCase.Params
import javax.inject.Inject

class GetInfoPicUseCase @Inject constructor(
    private val dataPicRepository: PicRepository,
    private val domainErrorFactory: DomainErrorFactory
) : SuspendableResultUseCase<Either<HandledError, PicImageEntity>, Params> {

    override suspend fun execute(params: Params?): Either<HandledError, PicImageEntity> {
        return params?.let {
            dataPicRepository.getPicInfo(params.id)
                .fold(functionLeft = {
                    Either.Left(domainErrorFactory.resolveError(it))
                },
                    functionRight = {
                        Either.Right(it)
                    })
        } ?: Either.Left(HandledError.ExceptionError())
    }

    data class Params(
        val id: String
    )
}
