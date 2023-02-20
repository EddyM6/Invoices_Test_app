package com.xmm_homework.invoice_info_screen.viewmodels.di

import com.xmm_homework.invoice_info_screen.viewmodels.InvoiceInfoViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule = module {
    viewModel {
        InvoiceInfoViewModel(
            getInvoicesInfoUseCase = get(),
            uiMappers = get()
        )
    }
}