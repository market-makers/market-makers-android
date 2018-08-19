package com.marketmakers.mobile.repository.api

import com.marketmakers.mobile.model.Invoice
import com.marketmakers.mobile.model.UserInvoice
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST



interface UserAPI {
    @POST("users/{id}")
    fun createUser(@Body user: UserInvoice): Observable<Invoice>
}