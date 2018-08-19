package com.marketmakers.mobile.repository.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.marketmakers.mobile.BuildConfig
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit



class BaseAPI {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.API_BASE_URL)
                .build()
    }
}