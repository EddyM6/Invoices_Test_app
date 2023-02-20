package com.xmm_homework.invoice_info_screen.models

import androidx.compose.runtime.Immutable
import com.xmm_homework.core.view_state.ViewState

@Immutable
internal data class InvoiceInfoScreenViewState(
    val viewState: ViewState<InvoicesInfoUiModel>,
) {
    companion object {
        fun initial() = InvoiceInfoScreenViewState(
            viewState = ViewState.Loading
        )
    }
}