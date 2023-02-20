package com.xmm_homework.domain.usecase

abstract class UseCase<P, R> {
    abstract suspend fun execute(param: P): R
}
