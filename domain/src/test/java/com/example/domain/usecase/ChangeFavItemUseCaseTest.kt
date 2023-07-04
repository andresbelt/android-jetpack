package com.example.domain.usecase

import com.example.commons.Either
import com.example.domain.CoroutineTestRule
import com.example.domain.utils.RandomFactory
import com.example.domain.commons.DomainErrorFactory
import com.example.domain.commons.HandledError
import com.example.domain.repository.PicRepository
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Test class of [ChangeFavItemUseCase]
 */
@ExperimentalCoroutinesApi
class ChangeFavItemUseCaseTest {
    @get:Rule
    val coroutinesRule = CoroutineTestRule()

    @MockK
    private lateinit var batchRepository: PicRepository

    @MockK
    private lateinit var domainErrorFactory: DomainErrorFactory

    private lateinit var useCase: ChangeFavItemUseCase

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        useCase = ChangeFavItemUseCase(batchRepository, domainErrorFactory)
    }

    @Test
    fun `Given Params When ChangeFavItemUseCase is executed Then returns Success state`() =
        coroutinesRule.runBlockingTest {
            //Given
            val id = RandomFactory.generateString()
            val fav = RandomFactory.generateBoolean()
            val params = ChangeFavItemUseCase.Params(id, fav)

            //When
            coEvery { batchRepository.changeFavItem(any(), any()) } returns Either.Right(
                0
            )

            val result = useCase.execute(params = params)

            //Verify
            coVerifySequence {
                batchRepository.changeFavItem(id, fav)
            }
            assert(result is Either.Right)
            assert((result as Either.Right).value == 0)
        }

    @Test
    fun `Given Params When ChangeFavItemUseCase is executed Then returns Error state`() =
        coroutinesRule.runBlockingTest {
            //Given
            val params = null
            val result = useCase.execute(params = params)

            //Verify
            assert(result is Either.Left)
            assert((result as Either.Left).error is HandledError.ExceptionError)
            Assert.assertEquals(
                (result.error as HandledError.ExceptionError).message,
                HandledError.ExceptionError().message
            )
        }
}
