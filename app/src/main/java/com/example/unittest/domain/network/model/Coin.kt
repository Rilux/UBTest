package com.example.unittest.domain.network.model


import com.google.gson.annotations.SerializedName

data class Coin(
    @SerializedName("uuid")
    val uuid: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("color")
    val color: String?,
    @SerializedName("iconUrl")
    val iconUrl: String,
    @SerializedName("marketCap")
    val marketCap: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("listedAt")
    val listedAt: Int?,
    @SerializedName("tier")
    val tier: Int,
    @SerializedName("change")
    val change: String,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("sparkline")
    val sparkline: List<String>,
    @SerializedName("lowVolume")
    val lowVolume: Boolean,
    @SerializedName("coinrankingUrl")
    val coinrankingUrl: String,
    @SerializedName("24hVolume")
    val hVolume: String,
    @SerializedName("btcPrice")
    val btcPrice: String
)