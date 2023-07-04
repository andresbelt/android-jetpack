package com.platzi.android.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.ExperimentalPagingApi
import app.cash.turbine.test
import com.example.domain.commons.DomainErrorFactory
import com.example.domain.repository.PagingRepository
import com.example.domain.repository.PicRepository
import com.example.domain.usecase.ChangeFavItemUseCase
import com.example.domain.usecase.GetPhotosListUseCase
import com.platzi.android.CoroutineTestRule
import com.platzi.android.FakePicImgData.getFakePagingData
import com.platzi.android.presentation.ui.screens.home.HomeViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Test class of [HomeViewModel]
 */
@ExperimentalCoroutinesApi
@ExperimentalPagingApi
class HomeViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    @MockK
    private lateinit var getPhotosListUseCase: GetPhotosListUseCase

    @MockK
    private lateinit var changeFavUseCase: ChangeFavItemUseCase

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `Given a LoadOrdersEvent event when RecentOrdersViewModel receives Success and lastOrders is empty return a RecentOrdersScreenViewState with showNoOrders in true`() = coroutineTestRule.runBlockingTest {
        //Given
        val list = getFakePagingData()
        coEvery { getPhotosListUseCase.execute() } returns list

        viewModel = HomeViewModel(getPhotosListUseCase, changeFavUseCase)

        //Verify
        coVerify { getPhotosListUseCase.execute() }

        viewModel.stateStream.test {
            assertEquals(false, awaitItem().isLoading)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
