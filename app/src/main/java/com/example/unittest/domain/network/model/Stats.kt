package com.example.unittest.domain.network.model


import com.google.gson.annotations.SerializedName

data class Stats(
    @SerializedName("total")
    val total: Int,
    @SerializedName("totalCoins")
    val totalCoins: Int,
    @SerializedName("totalMarkets")
    val totalMarkets: Int,
    @SerializedName("totalExchanges")
    val totalExchanges: Int,
    @SerializedName("totalMarketCap")
    val totalMarketCap: String,
    @SerializedName("total24hVolume")
    val total24hVolume: String
)