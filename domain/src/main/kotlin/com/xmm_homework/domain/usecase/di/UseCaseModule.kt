package com.xmm_homework.domain.usecase.di

import com.xmm_homework.domain.usecase.invoice_info_details.GetInvoicesInfoDetailsUseCase
import com.xmm_homework.domain.usecase.invoices_info.GetInvoicesInfoUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single {
        GetInvoicesInfoUseCase(
            invoicesInfoRepository = get()
        )
    }
    factory {
        GetInvoicesInfoDetailsUseCase(
            invoicesInfoRepository = get()
        )
    }
}