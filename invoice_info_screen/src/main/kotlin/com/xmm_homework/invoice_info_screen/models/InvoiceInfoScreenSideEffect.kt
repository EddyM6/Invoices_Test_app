package com.xmm_homework.invoice_info_screen.models

import androidx.compose.runtime.Immutable

@Immutable
internal sealed class InvoiceInfoScreenSideEffect {
    @Immutable
    data class NavigateToInvoiceDetailsScreen(
        val data: ItemUiModel,
        val isItemClicked: Boolean
    ) : InvoiceInfoScreenSideEffect()
}
