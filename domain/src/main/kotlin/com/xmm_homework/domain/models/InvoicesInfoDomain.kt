package com.xmm_homework.domain.models


data class InvoicesInfoDomain(
    val items: List<ItemDomain>?
)

data class ItemDomain(
    val date: String,
    val id: String,
    val details: List<DetailsDomain>
) {
    data class DetailsDomain(
        val id: String,
        val name: String,
        val priceInCents: Int,
        val quantity: Int
    )
}