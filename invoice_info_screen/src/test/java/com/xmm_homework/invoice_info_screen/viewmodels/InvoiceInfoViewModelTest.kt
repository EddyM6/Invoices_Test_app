package com.xmm_homework.invoice_info_screen.viewmodels

import android.util.MalformedJsonException
import com.xmm_homework.core.view_state.ViewState
import com.xmm_homework.domain.models.InvoicesInfoDomain
import com.xmm_homework.domain.models.ItemDomain
import com.xmm_homework.domain.usecase.invoices_info.GetInvoicesInfoUseCase
import com.xmm_homework.domain.util.RequestResult
import com.xmm_homework.invoice_info_screen.mappers.InvoiceInfoDomainToUiMapper
import com.xmm_homework.invoice_info_screen.models.InvoicesInfoUiModel
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
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner.Silent::class)
internal class InvoiceInfoViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    lateinit var uiMappers: InvoiceInfoDomainToUiMapper

    @Mock
    lateinit var getInvoicesInfoUseCase: GetInvoicesInfoUseCase

    private lateinit var invoiceInfoViewModel: InvoiceInfoViewModel

    @Before
    fun beforeTest() {
        invoiceInfoViewModel = InvoiceInfoViewModel(
            getInvoicesInfoUseCase = getInvoicesInfoUseCase,
            uiMappers = uiMappers
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test load invoice info data with Success state`() = runTest {
        // given
        `when`(getInvoicesInfoUseCase.execute(Unit)).thenReturn(
            RequestResult.Success(
                data = InvoicesInfoDomain(
                    items = listOf(
                        ItemDomain(
                            date = "",
                            id = "",
                            details = listOf(
                                ItemDomain.DetailsDomain(
                                    id = "",
                                    name = "",
                                    priceInCents = 45,
                                    quantity = 400
                                )
                            )
                        )
                    )
                )
            )
        )
        val invoiceInfoViewState = mutableListOf<ViewState<InvoicesInfoUiModel>>()
        val collectViewState = launch(UnconfinedTestDispatcher()){
            invoiceInfoViewModel.viewStateMutableStateFlow.toList(invoiceInfoViewState)
        }
        // when
        invoiceInfoViewModel.loadData()
        // then
        verify(getInvoicesInfoUseCase, times(1)).execute(Unit)
        TestCase.assertTrue(invoiceInfoViewState.firstOrNull() is ViewState.Loading)
        TestCase.assertTrue(invoiceInfoViewState.lastOrNull() is ViewState.Success)
        collectViewState.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test load invoice info data with Success state and empty data`() = runTest {
        // given
        `when`(getInvoicesInfoUseCase.execute(Unit)).thenReturn(
            RequestResult.Success(
                data = InvoicesInfoDomain(
                    items = emptyList()
                )
            )
        )
        val invoiceInfoViewState = mutableListOf<ViewState<InvoicesInfoUiModel>>()
        val collectViewState = launch(UnconfinedTestDispatcher()){
            invoiceInfoViewModel.viewStateMutableStateFlow.toList(invoiceInfoViewState)
        }
        // when
        invoiceInfoViewModel.loadData()
        // then
        verify(getInvoicesInfoUseCase, times(1)).execute(Unit)
        TestCase.assertTrue(invoiceInfoViewState.firstOrNull() is ViewState.Loading)
        TestCase.assertTrue(invoiceInfoViewState.lastOrNull() is ViewState.Empty)
        collectViewState.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test load invoice info data with Error state with Malformed `() = runTest {
        // given
        `when`(getInvoicesInfoUseCase.execute(Unit)).thenReturn(
            RequestResult.Error(
                MalformedJsonException("Malformed data")
                )
        )
        val invoiceInfoViewState = mutableListOf<ViewState<InvoicesInfoUiModel>>()
        val collectViewState = launch(UnconfinedTestDispatcher()){
            invoiceInfoViewModel.viewStateMutableStateFlow.toList(invoiceInfoViewState)
        }
        // when
        invoiceInfoViewModel.loadData()
        // then
        verify(getInvoicesInfoUseCase, times(1)).execute(Unit)
        TestCase.assertTrue(invoiceInfoViewState.firstOrNull() is ViewState.Loading)
        TestCase.assertTrue(invoiceInfoViewState.lastOrNull() is ViewState.Malformed)
        collectViewState.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test load invoice info data with Error state (Network error)`() = runTest {
        // given
        `when`(getInvoicesInfoUseCase.execute(Unit)).thenReturn(
            RequestResult.Error(
               Exception()
            )
        )
        val invoiceInfoViewState = mutableListOf<ViewState<InvoicesInfoUiModel>>()
        val collectViewState = launch(UnconfinedTestDispatcher()){
            invoiceInfoViewModel.viewStateMutableStateFlow.toList(invoiceInfoViewState)
        }
        // when
        invoiceInfoViewModel.loadData()
        // then
        verify(getInvoicesInfoUseCase, times(1)).execute(Unit)
        TestCase.assertTrue(invoiceInfoViewState.firstOrNull() is ViewState.Loading)
        TestCase.assertTrue(invoiceInfoViewState.lastOrNull() is ViewState.Error)
        collectViewState.cancel()
    }
}