package com.xmm_homework.data.repositoryImpl

import com.xmm_homework.data.invoices_info.data_source.locale.InvoicesInfoLocalDataSource
import com.xmm_homework.data.invoices_info.data_source.remote.InvoicesInfoRemoteDataSource
import com.xmm_homework.domain.models.InvoiceRequestTypeEnum
import com.xmm_homework.domain.models.InvoicesInfoDomain
import com.xmm_homework.domain.repository.InvoicesInfoRepository
import com.xmm_homework.domain.util.RequestResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class InvoicesInfoRepositoryImpl(
    private val invoicesInfoRemoteDataSource: InvoicesInfoRemoteDataSource,
    private val invoicesInfoLocalDataSource: InvoicesInfoLocalDataSource,
    private val coroutineDispatcher: CoroutineDispatcher
) : InvoicesInfoRepository {

    override suspend fun getValidInvoicesInfo(): RequestResult<InvoicesInfoDomain> =
        withContext(context = coroutineDispatcher) {
            invoicesInfoRemoteDataSource.getValidInvoicesInfo()
        }

    override suspend fun getMalformedInvoicesInfo(): RequestResult<InvoicesInfoDomain> =
        withContext(context = coroutineDispatcher) {
            invoicesInfoRemoteDataSource.getMalformedInvoicesInfo()
        }

    override suspend fun getEmptyInvoicesInfo(): RequestResult<InvoicesInfoDomain> =
        withContext(context = coroutineDispatcher) {
            invoicesInfoRemoteDataSource.getEmptyInvoicesInfo()
        }

    override suspend fun getInvoiceRequestType(): InvoiceRequestTypeEnum =
        invoicesInfoLocalDataSource.getInvoiceRequestType()

    override suspend fun setInvoiceRequestType(requestTypeEnum: InvoiceRequestTypeEnum) =
        invoicesInfoLocalDataSource.setInvoiceRequestType(invoiceRequestTypeEnum = requestTypeEnum)

}