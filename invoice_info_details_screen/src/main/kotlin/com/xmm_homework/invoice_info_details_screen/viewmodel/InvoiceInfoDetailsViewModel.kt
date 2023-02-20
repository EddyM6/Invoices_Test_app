package com.xmm_homework.invoice_info_details_screen.viewmodel

import android.util.MalformedJsonException
import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.Stable
import androidx.lifecycle.viewModelScope
import com.xmm_homework.core.view_models.BaseViewModel
import com.xmm_homework.core.view_state.ViewState
import com.xmm_homework.domain.models.InvoiceInfoDetailsDomain
import com.xmm_homework.domain.usecase.invoice_info_details.GetInvoicesInfoDetailsUseCase
import com.xmm_homework.domain.util.RequestResult
import com.xmm_homework.domain.util.error
import com.xmm_homework.invoice_info_details_screen.mappers.InvoiceDetailsDomainToUiMapper
import com.xmm_homework.invoice_info_details_screen.models.InvoiceDetailsUiModel
import com.xmm_homework.invoice_info_details_screen.models.InvoiceInfoDetailsSideEffect
import com.xmm_homework.invoice_info_details_screen.models.InvoiceInfoDetailsViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@Stable
internal class InvoiceInfoDetailsViewModel(
    private val getInvoicesInfoDetailsUseCase: GetInvoicesInfoDetailsUseCase,
    private val detailsUiMapper: InvoiceDetailsDomainToUiMapper
) : ContainerHost<InvoiceInfoDetailsViewState, InvoiceInfoDetailsSideEffect>, BaseViewModel() {

    override val container =
        container<InvoiceInfoDetailsViewState, InvoiceInfoDetailsSideEffect>(
            InvoiceInfoDetailsViewState.initial()
        )

    val isRefreshing = MutableStateFlow(false)
    @VisibleForTesting
    var invoiceId: String = ""
    @VisibleForTesting
    val viewStateMutableStateFlow =
        MutableStateFlow<ViewState<List<InvoiceDetailsUiModel>>>(ViewState.Loading)

    fun loadDetailsById(invoiceId: String) {
        this.invoiceId = invoiceId
        viewModelScope.launch {
            val response = getInvoicesInfoDetailsUseCase.execute(param = invoiceId)
            postViewState(
                toViewState(responseData = response)
            )
        }
    }

    fun setRefresh(isRefresh: Boolean) {
        viewModelScope.launch {
            isRefreshing.emit(isRefresh)
        }
    }

    fun refresh() {
        viewModelScope.launch {
            setRefresh(isRefresh = true)
            postViewState(ViewState.Loading)
            delay(1000)
            loadDetailsById(invoiceId = invoiceId)
        }
    }

    private fun toViewState(responseData: RequestResult<List<InvoiceInfoDetailsDomain>>) =
        when (responseData) {
            is RequestResult.Success -> {
                if (responseData.data.isEmpty()) {
                    ViewState.Empty
                } else {
                    ViewState.Success(data = responseData.data.map { detailsUiMapper.mapFrom(it) })
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

    private suspend fun postViewState(newViewState: ViewState<List<InvoiceDetailsUiModel>>) {
        viewStateMutableStateFlow.emit(newViewState)
        reducer()
    }

    private fun reducer() {
        intent {
            reduce {
                InvoiceInfoDetailsViewState(viewStateMutableStateFlow.value)
            }
        }
    }
}