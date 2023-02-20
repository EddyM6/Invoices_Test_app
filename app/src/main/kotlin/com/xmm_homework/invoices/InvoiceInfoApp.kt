package com.xmm_homework.invoices

import android.app.Application
import com.xmm_homework.core.di.coreModules
import com.xmm_homework.data.di.dataModules
import com.xmm_homework.domain.di.domainModules
import com.xmm_homework.invoice_info_details_screen.di.invoiceInfoDetailsScreenModules
import com.xmm_homework.invoice_info_screen.di.invoiceInfoScreenModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class InvoiceInfoApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@InvoiceInfoApp)
            modules(coreModules + domainModules + dataModules + invoiceInfoScreenModules + invoiceInfoDetailsScreenModules)
        }
    }
}