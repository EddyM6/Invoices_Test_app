package com.xmm_homework.domain.repository

import com.xmm_homework.domain.models.InvoiceRequestTypeEnum
import com.xmm_homework.domain.models.InvoicesInfoDomain
import com.xmm_homework.domain.util.RequestResult

interface InvoicesInfoRepository {
    suspend fun getValidInvoicesInfo(): RequestResult<InvoicesInfoDomain>
    suspend fun getMalformedInvoicesInfo(): RequestResult<InvoicesInfoDomain>
    suspend fun getEmptyInvoicesInfo(): RequestResult<InvoicesInfoDomain>
    suspend fun getInvoiceRequestType(): InvoiceRequestTypeEnum
    suspend fun setInvoiceRequestType(requestTypeEnum: InvoiceRequestTypeEnum)
}