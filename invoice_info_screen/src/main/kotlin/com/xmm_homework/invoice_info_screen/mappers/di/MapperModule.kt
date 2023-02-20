package com.xmm_homework.invoice_info_screen.mappers.di

import com.xmm_homework.invoice_info_screen.mappers.InvoiceInfoDomainToUiMapper
import org.koin.dsl.module

val uiMapperModule = module {
    factory { InvoiceInfoDomainToUiMapper(context = get()) }
}