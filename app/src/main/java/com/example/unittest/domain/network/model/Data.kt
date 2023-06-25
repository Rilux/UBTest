package com.example.unittest.domain.network.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("stats")
    val stats: Stats,
    @SerializedName("coins")
    val coins: List<Coin>
)