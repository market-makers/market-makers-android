package com.marketmakers.mobile.model

import java.io.Serializable

class Address (val id: Int,
               val street: String,
               val neighborhood: String,
               val city: String,
               val state: String,
               val zipCode: String,
               val complement: String) : Serializable