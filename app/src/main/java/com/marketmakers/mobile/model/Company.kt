package com.marketmakers.mobile.model

import java.io.Serializable

class Company(val id: Int,
              val password: String,
              val patner: Boolean,
              val name: String,
              val companyName: String,
              val cnpj: String,
              val email: String,
              val type: String,
              val categories: String,
              val address: Address) : Serializable