package com.xmm_homework.data.invoices_info.data_source.remote.models


import com.google.gson.annotations.SerializedName
import com.xmm_homework.domain.models.InvoicesInfoDomain
import com.xmm_homework.domain.models.ItemDomain
import com.xmm_homework.core.mapper.Mappable

internal data class InvoicesInfoDto(
    @SerializedName("items")
    val items: List<ItemDto>?
) : Mappable<InvoicesInfoDomain> {
    override fun map(): InvoicesInfoDomain =
        InvoicesInfoDomain(
            items = items?.map { itemDto ->
                ItemDomain(
                    date = itemDto.date.orEmpty(),
                    id = itemDto.id.orEmpty(),
                    details = itemDto.details?.map {
                        ItemDomain.DetailsDomain(
                            id = it.id.orEmpty(),
                            name = it.name.orEmpty(),
                            priceInCents = it.priceInCents ?: 0,
                            quantity = it.quantity ?: 0
                        )
                    } ?: emptyList()

                )
            }
        )
}

internal data class ItemDto(
    @SerializedName("date")
    val date: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("items")
    val details: List<DetailsDto>?
) {
    data class DetailsDto(
        @SerializedName("id")
        val id: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("priceinCents")
        val priceInCents: Int?,
        @SerializedName("quantity")
        val quantity: Int?
    )
}