package com.xmm_homework.domain.usecase

abstract class SuspendedExecutionUseCase<P, R> {
    abstract fun execute(param: P): R
}
