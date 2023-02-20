package com.xmm_homework.data.invoices_info.data_source.locale

import com.xmm_homework.data.api.InvoicesAppRestServiceApi
import com.xmm_homework.domain.models.InvoiceRequestTypeEnum
import com.xmm_homework.domain.models.InvoicesInfoDomain
import com.xmm_homework.domain.util.RequestResult

internal interface InvoicesInfoLocalDataSource {

    suspend fun setInvoiceRequestType(invoiceRequestTypeEnum: InvoiceRequestTypeEnum)
    suspend fun getInvoiceRequestType():InvoiceRequestTypeEnum
}