package com.example.data.datasource.local

import androidx.paging.PagingSource.LoadParams
import androidx.paging.PagingSource.LoadResult
import com.example.commons.Either
import com.example.data.CoroutineTestRule
import com.example.data.model.PicImageEntity
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PicImgLocalDataSourceDataSourceTest {
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @MockK
    lateinit var picImageDatabase: PicImageDatabase

    private lateinit var picImgLocalDataSource: PicImgLocalDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        picImgLocalDataSource = PicImgLocalDataSource(picImageDatabase)
    }

    private fun verifyAllMocks() = confirmVerified(picImageDatabase)

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `Given a id of PicImg When getPicInfo is invoked Then returned a object that will be persisted correctly`() = coroutineTestRule.runBlockingTest {
        //Given
        val imgId = "0"
        val expected = PicImageEntity()
        coEvery { picImageDatabase.unsplashImageDao().getPicInfo(imgId) } returns PicImageEntity()

        // When
        val result = picImgLocalDataSource.getPicInfo(imgId)

        // Then
        coVerifySequence {  picImageDatabase.unsplashImageDao().getPicInfo(imgId) }
        Assert.assertTrue(result is Either.Right)
        assertEquals(expected, (result as Either.Right).value)
        verifyAllMocks()
    }

    @Test
    fun `Given a change when changeFavItem is invoked Then returned a int`() = coroutineTestRule.runBlockingTest {
        //Given
        val imgId = "0"
        val fav = true
        val expected = 0
        coEvery { picImageDatabase.unsplashImageDao().changeFavItem(imgId, fav) } returns 0

        // When
        val result = picImgLocalDataSource.changeFavItem(imgId, fav)

        // Then
        coVerifySequence {  picImageDatabase.unsplashImageDao().changeFavItem(imgId, fav) }
        Assert.assertTrue(result is Either.Right)
        assertEquals(expected, (result as Either.Right).value)
        verifyAllMocks()
    }


    @Test
    fun `when getAllImages is invoked pagingSource is expected`() = coroutineTestRule.runBlockingTest {
        //Given
        val mockPaging = MockPagingSource()

        coEvery { picImageDatabase.unsplashImageDao().getAllPic() } returns mockPaging

        // When
        val result = picImgLocalDataSource.getAllImages()

        // Then
        coVerifySequence {  picImageDatabase.unsplashImageDao().getAllPic() }
        assertEquals(mockPaging, result)
        verifyAllMocks()
    }

    @Test
    fun `when setData into paging check the result is expected`() = coroutineTestRule.runBlockingTest {
        //Given
        val mockPagingSource = MockPagingSource()

        // Set the data to be returned by the PagingSource
        val testData = listOf(PicImageEntity(), PicImageEntity(), PicImageEntity())
        mockPagingSource.setData(testData)
        val loadParams = LoadParams.Refresh(0, 2, false)
        val expectedLoadResult = LoadResult.Page(
            data = testData.subList(0, 2),
            prevKey = null,
            nextKey = 1
        )

        // Call the load method and assert the result
        val actualLoadResult = runBlocking { mockPagingSource.load(loadParams) }
        assertEquals(expectedLoadResult, actualLoadResult)
    }

    @Test
    fun `when deleteAllImages invoked Then returned a int`() = coroutineTestRule.runBlockingTest {
        //Given
        val listPic = listOf<PicImageEntity>()
        val expected = Unit
        coEvery { picImageDatabase.unsplashImageDao().addPics(listPic) } returns Unit

        // When
        val result = picImageDatabase.unsplashImageDao().addPics(listPic)

        // Then
        coVerifySequence { picImageDatabase.unsplashImageDao().addPics(listPic) }
        assertEquals(expected, result)
        verifyAllMocks()
    }

}
