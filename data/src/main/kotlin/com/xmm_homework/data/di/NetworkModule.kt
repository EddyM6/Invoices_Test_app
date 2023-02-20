package com.xmm_homework.data.di

import com.xmm_homework.data.BuildConfig
import com.xmm_homework.data.api.BaseInterceptor
import com.xmm_homework.data.api.InvoicesAppRestServiceApi
import com.xmm_homework.data.api.NetworkLogInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

internal val networkModule = module {
    single { getOkHttpClient(baseInterceptor = get(), networkLogInterceptor = get()) }
    single { getRetrofit(okHttpClient = get()) }
    single { getInvoicesAppRestService(retrofit = get()) }
    single { BaseInterceptor() }
    single { NetworkLogInterceptor() }

}

private fun getRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit
    .Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    .build()

private fun getInvoicesAppRestService(retrofit: Retrofit): InvoicesAppRestServiceApi =
    retrofit.create(InvoicesAppRestServiceApi::class.java)

private fun getOkHttpClient(
    baseInterceptor: BaseInterceptor,
    networkLogInterceptor: NetworkLogInterceptor,
): OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(baseInterceptor)
    .addInterceptor(networkLogInterceptor)
    .build()

