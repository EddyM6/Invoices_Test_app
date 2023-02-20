package com.xmm_homework.data.di

import com.xmm_homework.core.di.DispatchersName
import com.xmm_homework.data.repositoryImpl.InvoicesInfoRepositoryImpl
import com.xmm_homework.domain.repository.InvoicesInfoRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val repositoryModule = module {

    single<InvoicesInfoRepository> {
        InvoicesInfoRepositoryImpl(
            invoicesInfoRemoteDataSource = get(),
            invoicesInfoLocalDataSource = get(),
            coroutineDispatcher = get(qualifier = named(DispatchersName.IO))
        )
    }
}