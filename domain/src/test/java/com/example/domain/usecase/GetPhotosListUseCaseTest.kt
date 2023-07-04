package com.example.domain.usecase
import androidx.paging.map
import com.example.domain.CoroutineTestRule
import com.example.domain.repository.PagingRepository
import com.example.domain.utils.FakePicImgData
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Test class of [GetPhotosListUseCase]
 */
@ExperimentalCoroutinesApi
class GetPhotosListUseCaseTest {
    @get:Rule
    val coroutinesRule = CoroutineTestRule()

    @MockK
    private lateinit var batchRepository: PagingRepository

    private lateinit var useCase: GetPhotosListUseCase

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        useCase = GetPhotosListUseCase(batchRepository)
    }

    private fun verifyAllMocks() {
        confirmVerified(
            batchRepository
        )
    }

    @Test
    fun `Given Params When ChangeFavItemUseCase is executed Then returns Success state`() =
        coroutinesRule.runBlockingTest {
            //When
            coEvery { batchRepository.getAllImages() } returns FakePicImgData.getFakePagingData()

            //Verify
            val collectJob = launch {
                val resultFlow = useCase.execute()

                resultFlow.collect { it ->
                    it.map {
                        assertEquals(it.fav, true)
                    }

                    coVerifyOrder {
                        batchRepository.getAllImages()
                    }

                    verifyAllMocks()
                }
            }
            collectJob.cancel()
        }
}
