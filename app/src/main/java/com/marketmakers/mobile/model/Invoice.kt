package com.marketmakers.mobile.model

class Invoice(val id: Int,
               val code: String,
               val data: Long,
               val company: Company,
               val products: List<Product>,
               val user: User)