package com.xmm_homework.data.invoices_info.data_source.locale

import com.xmm_homework.domain.models.InvoiceRequestTypeEnum
import com.xmm_homework.domain.service.ConfigDataService

internal class InvoicesInfoLocalDataSourceImpl(private val configDataService: ConfigDataService) :
    InvoicesInfoLocalDataSource {
    companion object {
        private const val KEY_INVOICE_REQUEST_TYPE = "Invoice_Request_Type"
    }

    override suspend fun setInvoiceRequestType(invoiceRequestTypeEnum: InvoiceRequestTypeEnum) =
        configDataService.save(key = KEY_INVOICE_REQUEST_TYPE, value = invoiceRequestTypeEnum.param)


    override suspend fun getInvoiceRequestType(): InvoiceRequestTypeEnum =
        when (configDataService.getInt(KEY_INVOICE_REQUEST_TYPE)) {
            0 -> InvoiceRequestTypeEnum.VALID
            1 -> InvoiceRequestTypeEnum.MALFORMED
            2 -> InvoiceRequestTypeEnum.EMPTY
            else -> InvoiceRequestTypeEnum.VALID
        }
}