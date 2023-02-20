package com.xmm_homework.data.invoices_info.data_source.remote

import com.xmm_homework.domain.models.InvoicesInfoDomain
import com.xmm_homework.domain.util.RequestResult

internal interface InvoicesInfoRemoteDataSource {

    suspend fun getValidInvoicesInfo(): RequestResult<InvoicesInfoDomain>
    suspend fun getMalformedInvoicesInfo(): RequestResult<InvoicesInfoDomain>
    suspend fun getEmptyInvoicesInfo(): RequestResult<InvoicesInfoDomain>
}