package com.example.domain.usecase

import com.example.commons.Either
import com.example.data.model.PicImageEntity
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
 * Test class of [GetInfoPicUseCase]
 */
@ExperimentalCoroutinesApi
class GetInfoPicUseCaseTest {
    @get:Rule
    val coroutinesRule = CoroutineTestRule()

    @MockK
    private lateinit var batchRepository: PicRepository

    @MockK
    private lateinit var domainErrorFactory: DomainErrorFactory

    private lateinit var useCase: GetInfoPicUseCase

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        useCase = GetInfoPicUseCase(batchRepository, domainErrorFactory)
    }

    @Test
    fun `Given Params When ChangeFavItemUseCase is executed Then returns Success state`() =
        coroutinesRule.runBlockingTest {
            //Given
            val id = RandomFactory.generateString()
            val params = GetInfoPicUseCase.Params(id)
            val expected = PicImageEntity()

            //When
            coEvery { batchRepository.getPicInfo(any()) } returns Either.Right(
                expected
            )

            val result = useCase.execute(params = params)

            //Verify
            coVerifySequence {
                batchRepository.getPicInfo(id)
            }
            assert(result is Either.Right)
            assert((result as Either.Right).value == expected)
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
