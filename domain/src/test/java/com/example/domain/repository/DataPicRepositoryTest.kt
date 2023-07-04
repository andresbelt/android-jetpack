package com.example.domain.repository

import com.example.commons.Either.Left
import com.example.commons.Either.Right
import com.example.data.datasource.local.PicImgLocalSource
import com.example.data.error.GenericUserErrorContainer
import com.example.data.error.UserError.Type.EXCEPTION
import com.example.data.model.PicImageEntity
import com.example.domain.CoroutineTestRule
import com.example.domain.utils.RandomFactory
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Test class of [DataPicRepository]
 */
@ExperimentalCoroutinesApi
class DataPicRepositoryTest {

    @get:Rule
    val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    @MockK
    lateinit var dataSource: PicImgLocalSource

    lateinit var repository: DataPicRepository

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        repository = DataPicRepository(dataSource)
    }

    @Test
    fun `given data source return object when getPicInfo then return object picImage`() = coroutineTestRule.runBlockingTest {
        //Given
        val id = RandomFactory.generateString()

        coEvery { dataSource.getPicInfo(id) } returns Right(PicImageEntity())

        //When
        val picInfo = repository.getPicInfo(id)

        //Verify
        coVerifySequence {
            dataSource.getPicInfo(id)
        }
        assertTrue(picInfo is Right)
    }

    @Test
    fun `given data source return error when getPicInfo then return error`() = coroutineTestRule.runBlockingTest {
        //Given
        val id = RandomFactory.generateString()
        val userErrorContainer = GenericUserErrorContainer(EXCEPTION, "Error getPicInfo")
        coEvery { dataSource.getPicInfo(id) } returns Left(userErrorContainer)

        //When
        val picInfo = repository.getPicInfo(id)

        //Verify
        coVerifySequence {
            dataSource.getPicInfo(id)
        }
        assertTrue(picInfo is Left)
    }

    @Test
    fun `given data source return 0 when changeFavItem then return the given 0`() = coroutineTestRule.runBlockingTest {
        //Given
        val id = RandomFactory.generateString()
        val fav = RandomFactory.generateBoolean()

        coEvery { dataSource.changeFavItem(id, fav) } returns Right(0)

        //When
        val picInfo = repository.changeFavItem(id, fav)

        //Verify
        coVerifySequence {
            dataSource.changeFavItem(id, fav)
        }
        assertTrue(picInfo is Right)
    }
}
