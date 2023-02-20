package com.xmm_homework.invoice_info_screen.models

import androidx.compose.runtime.Immutable

@Immutable
internal data class InvoicesInfoUiModel(
    val items: List<ItemUiModel>
)

@Immutable
internal data class ItemUiModel(
    val date: String,
    val id: String,
    val invoiceId: String,
    val details: List<DetailsUiModel>,
    val totalDetails: String
) {
    @Immutable
    internal data class DetailsUiModel(
        val id: String,
        val name: String,
        val priceInCents: Int,
        val quantity: Int
    )

    companion object {
        fun previewData(): List<ItemUiModel> =
            listOf(
                ItemUiModel(
                    id = "Id: f143404a-3e6c-4a61-98d0-5e9c3fe81d80",
                    invoiceId = "f143404a-3e6c-4a61-98d0-5e9c3fe81d80",
                    date = "Date: 2022-10-01T10:22:32",
                    details = listOf(
                        DetailsUiModel(
                            id = "Id: c100f400-0da3-4161-8f16-dba7d0b4356a",
                            name = "Name: Service #1",
                            priceInCents = 1,
                            quantity = 100
                        )
                    ),
                    totalDetails = "Total Details: 1"
                )
            )

    }
}