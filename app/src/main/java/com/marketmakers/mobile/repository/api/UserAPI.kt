package com.marketmakers.mobile.repository.api

class UserAPI : BaseAPI() {
    val api = getRetrofit().create(IUserAPI::class.java)
}