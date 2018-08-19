package com.marketmakers.mobile.model

import java.io.Serializable

class User(val id: Long,
           val userApp: String,
           val name: String,
           val email: String,
           val dots: Int) : Serializable