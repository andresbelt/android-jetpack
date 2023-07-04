package com.platzi.android.viewmodel

import app.cash.turbine.test
import com.example.commons.Either
import com.example.data.model.PicImageEntity
import com.example.domain.commons.DomainErrorFactory
import com.example.domain.repository.PicRepository
import com.example.domain.usecase.ChangeFavItemUseCase
import com.example.domain.usecase.GetInfoPicUseCase
import com.example.domain.usecase.GetInfoPicUseCase.Params
import com.platzi.android.CoroutineTestRule
import com.platzi.android.presentation.ui.screens.itemdetail.ItemDetailModel
import com.platzi.android.presentation.ui.screens.itemdetail.ItemDetailScreenViewEvent
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
 * Test class of [ItemDetailModel]
 */
@ExperimentalCoroutinesApi
class ItemDetailModelTest {

    @get:Rule
    var coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    @MockK
    private lateinit var getInfoPicUseCase: GetInfoPicUseCase
    @MockK
    private lateinit var changeFavUseCase: ChangeFavItemUseCase

    private lateinit var viewModel: ItemDetailModel

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = ItemDetailModel(getInfoPicUseCase, changeFavUseCase)
    }

    @Test
    fun `Given a LoadOrdersEvent event ders is empty return a with showNoOrders in true`() = coroutineTestRule.runBlockingTest {
        //Given
        val init = ItemDetailScreenViewEvent.InitEvent("0")
        val listOrderEmpty = PicImageEntity("0", fav= false)
        val params = Params("0")

        coEvery { getInfoPicUseCase.execute(params) } returns Either.Right(listOrderEmpty)

        viewModel.uiEvent(init)
        //Verify
        coVerify { getInfoPicUseCase.execute(params) }

        viewModel.stateStream.test {
            assertEquals(false, awaitItem().isLoading)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
