package com.example.domain.usecase

import com.example.commons.Either
import com.example.commons.SuspendableResultUseCase
import com.example.domain.commons.DomainErrorFactory
import com.example.domain.commons.HandledError
import com.example.domain.repository.PicRepository
import com.example.domain.usecase.ChangeFavItemUseCase.Params
import javax.inject.Inject

class ChangeFavItemUseCase @Inject constructor(
    private val picRepository: PicRepository,
    private val domainErrorFactory: DomainErrorFactory
) : SuspendableResultUseCase<Either<HandledError, Int>, Params> {

    override suspend fun execute(params: Params?): Either<HandledError, Int> {
        return params?.let {
            val result = picRepository.changeFavItem(params.id, params.fav)
            result.fold(functionLeft = {
                Either.Left(domainErrorFactory.resolveError(it))
            },
                functionRight = {
                    Either.Right(it)
                })
        } ?: Either.Left(HandledError.ExceptionError())
    }

    data class Params(
        val id: String,
        val fav: Boolean
    )

}
