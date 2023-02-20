package com.xmm_homework.data.invoices_info.data_source.remote

import com.google.gson.stream.MalformedJsonException
import com.xmm_homework.data.api.InvoicesAppRestServiceApi
import com.xmm_homework.domain.models.InvoicesInfoDomain
import com.xmm_homework.domain.util.RequestResult

internal class InvoicesInfoRemoteDataSourceImpl(private val invoiceApi: InvoicesAppRestServiceApi) :
    InvoicesInfoRemoteDataSource {
    override suspend fun getValidInvoicesInfo(): RequestResult<InvoicesInfoDomain> {
        return try {
            RequestResult.Success(data = invoiceApi.getValidInvoicesInfo().map())
        } catch (exception: Exception) {
            RequestResult.Error(exception)
        }
    }

    override suspend fun getMalformedInvoicesInfo(): RequestResult<InvoicesInfoDomain> {
        return try {
            RequestResult.Success(data = invoiceApi.getInvalidInvoicesInfo().map())
        } catch (exception: Exception) {
            when (exception) {
                is MalformedJsonException -> {
                    RequestResult.Error(android.util.MalformedJsonException("Malformed error"))
                }
                else -> {
                    RequestResult.Error(exception)
                }
            }
        }
    }

    override suspend fun getEmptyInvoicesInfo(): RequestResult<InvoicesInfoDomain> {
        return try {
            RequestResult.Success(data = invoiceApi.getEmptyInvoicesInfo().map())
        } catch (exception: Exception) {
            RequestResult.Error(exception)
        }
    }
}