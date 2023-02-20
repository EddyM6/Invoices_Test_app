package com.xmm_homework.domain.models

data class InvoiceInfoDetailsDomain(
    val id: String,
    val name: String,
    val priceInCents: Int,
    val quantity: Int
)
