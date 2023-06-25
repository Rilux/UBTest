package com.example.unittest.domain.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "stats")
    val stats: Stats,
    @Json(name = "coins")
    val coins: List<Coin>
)