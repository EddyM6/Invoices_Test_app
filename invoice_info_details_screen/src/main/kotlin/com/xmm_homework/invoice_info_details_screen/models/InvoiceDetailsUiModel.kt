package com.xmm_homework.invoice_info_details_screen.models

import androidx.compose.runtime.Immutable


@Immutable
internal data class InvoiceDetailsUiModel(
    val id: String,
    val name: String,
    val priceInCents: String,
    val quantity: String
) {
    companion object {
        fun previewData(): List<InvoiceDetailsUiModel> =
            listOf(
                InvoiceDetailsUiModel(
                    id = "Id: f143404a-3e6c-4a61-98d0-5e9c3fe81d80",
                    name = "Name: Service #1",
                    priceInCents = "Price in Cents: 750",
                    quantity = "Quantity: 1"
                ),
                InvoiceDetailsUiModel(
                    id = "Id: f143404a-3e6c-4a61-98d0-5e9c3fe81d80",
                    name = "Name: Service #2",
                    priceInCents = "Price in Cents: 1050",
                    quantity = "Quantity: 3"
                ),
                InvoiceDetailsUiModel(
                    id = "Id: f143404a-3e6c-4a61-98d0-5e9c3fe81d80",
                    name = "Name: Service #3",
                    priceInCents = "Price in Cents: 50",
                    quantity = "Quantity: 91"
                )
            )
    }
}