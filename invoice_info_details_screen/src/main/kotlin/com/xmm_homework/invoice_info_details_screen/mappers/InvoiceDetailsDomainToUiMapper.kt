package com.xmm_homework.invoice_info_details_screen.mappers

import android.content.Context
import com.xmm_homework.core.mapper.MappableFrom
import com.xmm_homework.domain.models.InvoiceInfoDetailsDomain
import com.xmm_homework.core.R
import com.xmm_homework.invoice_info_details_screen.models.InvoiceDetailsUiModel

internal class InvoiceDetailsDomainToUiMapper(private val context: Context) :
    MappableFrom<InvoiceInfoDetailsDomain, InvoiceDetailsUiModel> {

    override fun mapFrom(input: InvoiceInfoDetailsDomain): InvoiceDetailsUiModel =
        InvoiceDetailsUiModel(
            id = String.format(context.getString(R.string.id_formatted), input.id),
            name = String.format(
                context.getString(R.string.name_formatted),
                input.name
            ),
            priceInCents = String.format(
                context.getString(R.string.price_in_cents_formatted),
                input.priceInCents
            ),
            quantity = String.format(
                context.getString(R.string.quantity_formatted),
                input.quantity
            )
        )
}