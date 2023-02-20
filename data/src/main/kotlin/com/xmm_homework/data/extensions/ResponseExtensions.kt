package com.xmm_homework.data.extensions

import com.xmm_homework.core.mapper.Mappable
import com.xmm_homework.domain.util.RequestResult
import retrofit2.HttpException
import retrofit2.Response

internal fun <DomainModel, Dto : Mappable<DomainModel>> Response<Dto>.extractResult(): RequestResult<DomainModel> {
    val successfulResponse = body()?.map()
    if (isSuccessful && successfulResponse != null) {
        return RequestResult.Success(successfulResponse)
    }
    return RequestResult.Error(HttpException(this), errorCode = getErrorCode())
}

internal fun <DomainModel, Dto : Mappable<DomainModel>> Response<List<Dto>>.extractListResult(): RequestResult<List<DomainModel>> {
    val successfulResponse = body()?.map { it.map() }

    if (isSuccessful && successfulResponse != null) {
        return RequestResult.Success(successfulResponse)
    }

    return RequestResult.Error(HttpException(this), errorCode = getErrorCode())
}

internal fun Response<*>.getErrorCode(): Int {
    return code()
}

internal fun Response<*>.getException(): Exception {
    return Exception(message())
}
