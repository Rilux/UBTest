package com.example.unittest.domain.network.model


import com.example.unittest.domain.core.model.CoinPresentation
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Coin(
    @Json(name = "uuid")
    val uuid: String,
    @Json(name = "symbol")
    val symbol: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "color")
    val color: String?,
    @Json(name = "iconUrl")
    val iconUrl: String,
    @Json(name = "marketCap")
    val marketCap: String,
    @Json(name = "price")
    val price: String,
    @Json(name = "listedAt")
    val listedAt: Int?,
    @Json(name = "tier")
    val tier: Int,
    @Json(name = "change")
    val change: String,
    @Json(name = "rank")
    val rank: Int,
    @Json(name = "sparkline")
    val sparkline: List<String>,
    @Json(name = "lowVolume")
    val lowVolume: Boolean,
    @Json(name = "coinrankingUrl")
    val coinrankingUrl: String,
    @Json(name = "24hVolume")
    val hVolume: String,
    @Json(name = "btcPrice")
    val btcPrice: String
) {
    fun toCoinPresentation(): CoinPresentation =
        CoinPresentation(
            uuid = uuid,
            symbol = symbol,
            name = name,
            color = color,
            iconUrl = iconUrl,
            price = price
        )
}