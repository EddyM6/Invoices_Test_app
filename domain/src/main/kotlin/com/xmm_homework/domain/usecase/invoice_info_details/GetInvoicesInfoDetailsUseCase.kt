package com.xmm_homework.domain.usecase.invoice_info_details

import com.xmm_homework.domain.models.InvoiceInfoDetailsDomain
import com.xmm_homework.domain.repository.InvoicesInfoRepository
import com.xmm_homework.domain.usecase.UseCase
import com.xmm_homework.domain.util.RequestResult
import com.xmm_homework.domain.util.error

class GetInvoicesInfoDetailsUseCase(
    private val invoicesInfoRepository: InvoicesInfoRepository
) : UseCase<String, RequestResult<List<InvoiceInfoDetailsDomain>>>() {

    override suspend fun execute(param: String): RequestResult<List<InvoiceInfoDetailsDomain>> {
        return when (val response = invoicesInfoRepository.getValidInvoicesInfo()) {
            is RequestResult.Success -> {
                val invoiceDetailsListById =
                    response.data.items?.firstOrNull { it.id == param }?.details

                RequestResult.Success(data = invoiceDetailsListById?.map {
                    InvoiceInfoDetailsDomain(
                        id = it.id,
                        name = it.name,
                        priceInCents = it.priceInCents,
                        quantity = it.quantity
                    )
                } ?: emptyList()
                )
            }
            is RequestResult.Error -> {
                RequestResult.Error(response.error)
            }
        }
    }
}