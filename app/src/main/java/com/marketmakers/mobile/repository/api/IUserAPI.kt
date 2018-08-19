package com.marketmakers.mobile.repository.api

import com.marketmakers.mobile.model.Invoice
import com.marketmakers.mobile.model.User
import com.marketmakers.mobile.model.UserInvoice
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface IUserAPI {
    @POST("user/{id}/invoice")
    fun createInvoice(@Path("id") id: String, @Body user: UserInvoice): Observable<Invoice>

    @GET("user/{id}")
    fun getUser(@Path("id") id: String): Observable<User>
}