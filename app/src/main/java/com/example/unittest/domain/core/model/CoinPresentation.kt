package com.example.unittest.domain.core.model

data class CoinPresentation(
    val uuid: String,
    val symbol: String,
    val name: String,
    val color: String?,
    val iconUrl: String,
    val price: String,
)
