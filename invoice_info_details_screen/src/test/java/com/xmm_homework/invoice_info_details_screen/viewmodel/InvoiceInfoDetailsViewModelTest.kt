package com.xmm_homework.invoice_info_details_screen.viewmodel

import android.util.MalformedJsonException
import com.xmm_homework.core.view_state.ViewState
import com.xmm_homework.domain.models.InvoiceInfoDetailsDomain
import com.xmm_homework.domain.usecase.invoice_info_details.GetInvoicesInfoDetailsUseCase
import com.xmm_homework.domain.util.RequestResult
import com.xmm_homework.invoice_info_details_screen.mappers.InvoiceDetailsDomainToUiMapper
import com.xmm_homework.invoice_info_details_screen.models.InvoiceDetailsUiModel
import com.xmm_homework.test_utils.MainDispatcherRule
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import java.io.IOException

@RunWith(MockitoJUnitRunner.Silent::class)
internal class InvoiceInfoDetailsViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    lateinit var getInvoicesInfoDetailsUseCase: GetInvoicesInfoDetailsUseCase

    @Mock
    lateinit var detailsUiMapper: InvoiceDetailsDomainToUiMapper

    private lateinit var invoiceInfoDetailsViewModel: InvoiceInfoDetailsViewModel

    @Before
    fun beforeTest() {
        invoiceInfoDetailsViewModel = InvoiceInfoDetailsViewModel(
            getInvoicesInfoDetailsUseCase = getInvoicesInfoDetailsUseCase,
            detailsUiMapper = detailsUiMapper
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test load invoice info details data with Success state`() = runTest {
        // given
        val invoiceID = "invoice_info_id"
        Mockito.`when`(getInvoicesInfoDetailsUseCase.execute(invoiceID)).thenReturn(
            RequestResult.Success(
                data = listOf(
                    InvoiceInfoDetailsDomain(
                        id = "",
                        name = "",
                        priceInCents = 34,
                        quantity = 750
                    )
                )
            )
        )
        val invoiceInfoViewState = mutableListOf<ViewState<List<InvoiceDetailsUiModel>>>()
        val collectViewState = launch(UnconfinedTestDispatcher()) {
            invoiceInfoDetailsViewModel.viewStateMutableStateFlow.toList(invoiceInfoViewState)
        }
        // when
        invoiceInfoDetailsViewModel.loadDetailsById(invoiceID)
        // then
        verify(getInvoicesInfoDetailsUseCase, times(1)).execute(invoiceID)
        TestCase.assertTrue(invoiceInfoViewState.firstOrNull() is ViewState.Loading)
        TestCase.assertTrue(invoiceInfoViewState.lastOrNull() is ViewState.Success)
        TestCase.assertEquals(invoiceID,invoiceInfoDetailsViewModel.invoiceId)
        collectViewState.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test load invoice info details data with Success State and data empty `() = runTest {
        // given
        val invoiceID = "invoice_info_id"
        Mockito.`when`(getInvoicesInfoDetailsUseCase.execute(invoiceID)).thenReturn(
            RequestResult.Success(
                data = emptyList()
            )
        )
        val invoiceInfoViewState = mutableListOf<ViewState<List<InvoiceDetailsUiModel>>>()
        val collectViewState = launch(UnconfinedTestDispatcher()) {
            invoiceInfoDetailsViewModel.viewStateMutableStateFlow.toList(invoiceInfoViewState)
        }
        // when
        invoiceInfoDetailsViewModel.loadDetailsById(invoiceID)
        // then
        verify(getInvoicesInfoDetailsUseCase, times(1)).execute(invoiceID)
        TestCase.assertTrue(invoiceInfoViewState.firstOrNull() is ViewState.Loading)
        TestCase.assertTrue(invoiceInfoViewState.lastOrNull() is ViewState.Empty)
        TestCase.assertEquals(invoiceID,invoiceInfoDetailsViewModel.invoiceId)
        collectViewState.cancel()
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test load invoice info details data with Error State and Malformed `() = runTest {
        // given
        val invoiceID = "invoice_info_id"
        Mockito.`when`(getInvoicesInfoDetailsUseCase.execute(invoiceID)).thenReturn(
            RequestResult.Error(
                MalformedJsonException("Malformed data")
            )
        )
        val invoiceInfoViewState = mutableListOf<ViewState<List<InvoiceDetailsUiModel>>>()
        val collectViewState = launch(UnconfinedTestDispatcher()) {
            invoiceInfoDetailsViewModel.viewStateMutableStateFlow.toList(invoiceInfoViewState)
        }
        // when
        invoiceInfoDetailsViewModel.loadDetailsById(invoiceID)
        // then
        verify(getInvoicesInfoDetailsUseCase, times(1)).execute(invoiceID)
        TestCase.assertTrue(invoiceInfoViewState.firstOrNull() is ViewState.Loading)
        TestCase.assertTrue(invoiceInfoViewState.lastOrNull() is ViewState.Malformed)
        TestCase.assertEquals(invoiceID,invoiceInfoDetailsViewModel.invoiceId)
        collectViewState.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test load invoice info details data with Error State (Network Error)`() = runTest {
        // given
        val invoiceID = "invoice_info_id"
        Mockito.`when`(getInvoicesInfoDetailsUseCase.execute(invoiceID)).thenReturn(
            RequestResult.Error(
               IOException()
            )
        )
        val invoiceInfoViewState = mutableListOf<ViewState<List<InvoiceDetailsUiModel>>>()
        val collectViewState = launch(UnconfinedTestDispatcher()) {
            invoiceInfoDetailsViewModel.viewStateMutableStateFlow.toList(invoiceInfoViewState)
        }
        // when
        invoiceInfoDetailsViewModel.loadDetailsById(invoiceID)
        // then
        verify(getInvoicesInfoDetailsUseCase, times(1)).execute(invoiceID)
        TestCase.assertTrue(invoiceInfoViewState.firstOrNull() is ViewState.Loading)
        TestCase.assertTrue(invoiceInfoViewState.lastOrNull() is ViewState.Error)
        TestCase.assertEquals(invoiceID,invoiceInfoDetailsViewModel.invoiceId)
        collectViewState.cancel()
    }
}