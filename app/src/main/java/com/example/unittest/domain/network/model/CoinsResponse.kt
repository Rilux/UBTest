package com.example.unittest.domain.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoinsResponse(
    @Json(name = "status")
    val status: String,
    @Json(name = "data")
    val `data`: Data
)