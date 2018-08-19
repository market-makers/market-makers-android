package com.marketmakers.mobile.repository.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.marketmakers.mobile.BuildConfig
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import javax.xml.datatype.DatatypeConstants.SECONDS
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.xml.datatype.DatatypeConstants.SECONDS







open class BaseAPI {
    fun getRetrofit(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
                .readTimeout(BuildConfig.RETROFIT_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(BuildConfig.RETROFIT_TIMEOUT, TimeUnit.SECONDS)
                .build()

        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.API_BASE_URL)
                .client(okHttpClient)
                .build()
    }
}