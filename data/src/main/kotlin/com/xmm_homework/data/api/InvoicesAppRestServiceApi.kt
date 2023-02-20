package com.xmm_homework.data.api

import com.xmm_homework.data.api.ApiPath.GET_INVOICES_EMPTY_URL
import com.xmm_homework.data.api.ApiPath.GET_INVOICES_MALFORMED_URL
import com.xmm_homework.data.api.ApiPath.GET_INVOICES_VALID_INFO_URL
import com.xmm_homework.data.invoices_info.data_source.remote.models.InvoicesInfoDto
import retrofit2.http.GET

internal interface InvoicesAppRestServiceApi {

    @GET(GET_INVOICES_VALID_INFO_URL)
    suspend fun getValidInvoicesInfo(): InvoicesInfoDto

    @GET(GET_INVOICES_MALFORMED_URL)
    suspend fun getInvalidInvoicesInfo(): InvoicesInfoDto

    @GET(GET_INVOICES_EMPTY_URL)
    suspend fun getEmptyInvoicesInfo(): InvoicesInfoDto
}