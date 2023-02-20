package com.xmm_homework.domain.models

enum class InvoiceRequestTypeEnum(val param: Int) {
    VALID(param = 0), MALFORMED(param = 1), EMPTY(param = 2)
}