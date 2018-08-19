package com.marketmakers.mobile.repository.api

class PromotionAPI : BaseAPI() {
    val api = getRetrofit().create(IPromotionAPI::class.java)
}