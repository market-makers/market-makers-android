package com.marketmakers.mobile.model

import java.io.Serializable

class Promotion( val id: Long,
                 val type: String,
                 val value: Double,
                 val dots: Int,
                 val title: String,
                 val description: String,
                 val coupons: Long,
                 val expiration: Long,
                 val company: Company) : Serializable