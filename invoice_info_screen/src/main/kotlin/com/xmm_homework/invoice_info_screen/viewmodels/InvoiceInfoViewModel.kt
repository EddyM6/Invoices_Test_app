package com.xmm_homework.invoice_info_screen.viewmodels

import android.util.MalformedJsonException
import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.Stable
import androidx.lifecycle.viewModelScope
import com.xmm_homework.core.view_models.BaseViewModel
import com.xmm_homework.core.view_state.ViewState
import com.xmm_homework.domain.models.InvoicesInfoDomain
import com.xmm_homework.domain.usecase.invoices_info.GetInvoicesInfoUseCase
import com.xmm_homework.domain.util.RequestResult
import com.xmm_homework.domain.util.error
import com.xmm_homework.invoice_info_screen.mappers.InvoiceInfoDomainToUiMapper
import com.xmm_homework.invoice_info_screen.models.InvoiceInfoScreenSideEffect
import com.xmm_homework.invoice_info_screen.models.InvoiceInfoScreenViewState
import com.xmm_homework.invoice_info_screen.models.InvoicesInfoUiModel
import com.xmm_homework.invoice_info_screen.models.ItemUiModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@Stable
internal class InvoiceInfoViewModel(
    private val getInvoicesInfoUseCase: GetInvoicesInfoUseCase,
    private val uiMappers: InvoiceInfoDomainToUiMapper
) : ContainerHost<InvoiceInfoScreenViewState, InvoiceInfoScreenSideEffect>, BaseViewModel() {

    override val container = container<InvoiceInfoScreenViewState, InvoiceInfoScreenSideEffect>(
        InvoiceInfoScreenViewState.initial()
    )
    val isRefreshing = MutableStateFlow(false)
    @VisibleForTesting
    val viewStateMutableStateFlow =
        MutableStateFlow<ViewState<InvoicesInfoUiModel>>(ViewState.Loading)

    fun loadData() {
        viewModelScope.launch {
            val response = getInvoicesInfoUseCase.execute(param = Unit)
            postViewState(
                toViewState(responseData = response)
            )
        }
    }

    fun itemClicked(data: ItemUiModel) {
        intent {
            postSideEffect(
                InvoiceInfoScreenSideEffect.NavigateToInvoiceDetailsScreen(
                    data = data,
                    isItemClicked = true
                )
            )
        }
    }

    fun refresh() {
        viewModelScope.launch {
            setRefresh(isRefresh = true)
            postViewState(ViewState.Loading)
            delay(1000)
            loadData()
        }
    }

    fun setRefresh(isRefresh: Boolean) {
        viewModelScope.launch {
            isRefreshing.emit(isRefresh)
        }
    }

    private fun toViewState(responseData: RequestResult<InvoicesInfoDomain>) =
        when (responseData) {
            is RequestResult.Success -> {
                val response = responseData.data.items ?: emptyList()
                if (response.isEmpty()) {
                    ViewState.Empty
                } else {
                    ViewState.Success(data = uiMappers.mapFrom(input = responseData.data))
                }

            }
            is RequestResult.Error -> {
                val error = responseData.error?.cause
                val errorMessage = responseData.error?.message.orEmpty()
                when (responseData.error) {
                    is MalformedJsonException -> {
                        ViewState.Malformed
                    }
                    else -> {
                        ViewState.Error(
                            errorData = null,
                            error = error,
                            errorMessage = errorMessage
                        )
                    }
                }
            }
        }

    private suspend fun postViewState(newViewState: ViewState<InvoicesInfoUiModel>) {
        viewStateMutableStateFlow.emit(newViewState)
        reducer()
    }

    private fun reducer() {
        intent {
            reduce {
                InvoiceInfoScreenViewState(viewStateMutableStateFlow.value)
            }
        }
    }
}