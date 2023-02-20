package com.xmm_homework.data.di

import android.content.Context.MODE_PRIVATE
import com.xmm_homework.data.config.DefaultConfigDataService
import com.xmm_homework.data.invoices_info.data_source.locale.InvoicesInfoLocalDataSource
import com.xmm_homework.data.invoices_info.data_source.locale.InvoicesInfoLocalDataSourceImpl
import com.xmm_homework.data.invoices_info.data_source.remote.InvoicesInfoRemoteDataSource
import com.xmm_homework.data.invoices_info.data_source.remote.InvoicesInfoRemoteDataSourceImpl
import com.xmm_homework.domain.service.ConfigDataService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

internal val dataSourceModule = module {
    // remote
    single<InvoicesInfoRemoteDataSource> {
        InvoicesInfoRemoteDataSourceImpl(
            invoiceApi = get()
        )
    }


    // Locale
    single<ConfigDataService> {
        DefaultConfigDataService(
            sharedPreferences = androidContext().getSharedPreferences("app_config", MODE_PRIVATE)
        )
    }

    single<InvoicesInfoLocalDataSource> {
        InvoicesInfoLocalDataSourceImpl(
            configDataService = get()
        )
    }
}