package com.marketmakers.mobile.repository.api

import com.marketmakers.mobile.model.Promotion
import io.reactivex.Observable
import retrofit2.http.GET

interface IPromotionAPI {
    @GET("promotion")
    fun getPromotions(): Observable<List<Promotion>>
}