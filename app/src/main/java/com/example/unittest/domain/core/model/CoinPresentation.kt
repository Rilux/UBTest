package com.example.unittest.domain.core.model

import com.example.unittest.domain.cache.entities.CoinEntity

data class CoinPresentation(
    val uuid: String,
    val symbol: String,
    val name: String,
    val color: String?,
    val iconUrl: String,
    val price: String,
) {
    fun toCoinEntity(): CoinEntity = CoinEntity(
        uuid = uuid,
        symbol = symbol,
        name = name,
        color = color,
        iconUrl = iconUrl,
        price = price
    )
}
