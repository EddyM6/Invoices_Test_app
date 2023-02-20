package com.xmm_homework.invoice_info_screen.mappers

import android.content.Context
import com.xmm_homework.core.mapper.MappableFrom
import com.xmm_homework.domain.models.InvoicesInfoDomain
import com.xmm_homework.core.R
import com.xmm_homework.domain.util.DateUtils
import com.xmm_homework.invoice_info_screen.models.InvoicesInfoUiModel
import com.xmm_homework.invoice_info_screen.models.ItemUiModel

internal class InvoiceInfoDomainToUiMapper(private val context: Context) :
    MappableFrom<InvoicesInfoDomain, InvoicesInfoUiModel> {

    override fun mapFrom(input: InvoicesInfoDomain): InvoicesInfoUiModel =
        InvoicesInfoUiModel(
            items = input.items?.map {
                ItemUiModel(
                    date = String.format(
                        context.getString(R.string.date_formatted),
                        DateUtils.dateFormat(date = it.date)
                    ),
                    id = String.format(context.getString(R.string.id_formatted), it.id),
                    invoiceId = it.id,
                    details = it.details.map { detailsDomain ->
                        ItemUiModel.DetailsUiModel(
                            id = detailsDomain.id,
                            name = detailsDomain.name,
                            priceInCents = detailsDomain.priceInCents,
                            quantity = detailsDomain.quantity
                        )
                    },
                    totalDetails = String.format(
                        context.getString(R.string.total_details_formatted),
                        it.details.size
                    )
                )
            } ?: emptyList()
        )

}