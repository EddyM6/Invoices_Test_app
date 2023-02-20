package com.xmm_homework.invoice_info_details_screen.viewmodel.di

import com.xmm_homework.invoice_info_details_screen.viewmodel.InvoiceInfoDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val detailsViewModelModule = module {
    viewModel {
        InvoiceInfoDetailsViewModel(getInvoicesInfoDetailsUseCase = get(), detailsUiMapper = get())
    }
}