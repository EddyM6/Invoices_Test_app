package com.xmm_homework.invoice_info_details_screen.mappers

import org.koin.dsl.module

internal val uiMapperModule = module {
    factory { InvoiceDetailsDomainToUiMapper(context = get()) }
}