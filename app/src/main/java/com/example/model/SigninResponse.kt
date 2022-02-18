package com.example.model

import com.squareup.moshi.Json

data class SigninResponse(
//    val `data`: SigninData,
    @field:Json(name = "status")  val status: Int,
    @field:Json(name = "message")val message: String,
    @field:Json(name = "token")val token: String,
    @field:Json(name = "expiredTimeToken")val expiredTimeToken: String
)