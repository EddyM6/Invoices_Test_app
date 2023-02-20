package com.xmm_homework.domain.usecase.invoices_info

import com.xmm_homework.domain.models.InvoiceRequestTypeEnum
import com.xmm_homework.domain.models.InvoicesInfoDomain
import com.xmm_homework.domain.repository.InvoicesInfoRepository
import com.xmm_homework.domain.usecase.UseCase
import com.xmm_homework.domain.util.RequestResult

class GetInvoicesInfoUseCase(
    private val invoicesInfoRepository: InvoicesInfoRepository
) : UseCase<Unit, RequestResult<InvoicesInfoDomain>>() {
    override suspend fun execute(param: Unit): RequestResult<InvoicesInfoDomain> {

        return when (invoicesInfoRepository.getInvoiceRequestType()) {
            InvoiceRequestTypeEnum.VALID -> {
                invoicesInfoRepository.setInvoiceRequestType(InvoiceRequestTypeEnum.MALFORMED)
                invoicesInfoRepository.getValidInvoicesInfo()
            }
            InvoiceRequestTypeEnum.MALFORMED -> {
                invoicesInfoRepository.setInvoiceRequestType(InvoiceRequestTypeEnum.EMPTY)
                invoicesInfoRepository.getMalformedInvoicesInfo()
            }
            InvoiceRequestTypeEnum.EMPTY -> {
                invoicesInfoRepository.setInvoiceRequestType(InvoiceRequestTypeEnum.VALID)
                invoicesInfoRepository.getEmptyInvoicesInfo()
            }
        }
    }
}