package com.xmm_homework.invoice_info_details_screen.models

import androidx.compose.runtime.Immutable
import com.xmm_homework.core.view_state.ViewState

@Immutable
internal data class InvoiceInfoDetailsViewState(
    val viewState: ViewState<List<InvoiceDetailsUiModel>>
) {
    companion object {
        fun initial() = InvoiceInfoDetailsViewState(
            viewState = ViewState.Loading
        )
    }
}