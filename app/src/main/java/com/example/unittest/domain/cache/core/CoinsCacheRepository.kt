package com.example.unittest.domain.cache.core

import com.example.unittest.domain.core.model.CoinPresentation

interface CoinsCacheRepository {
    suspend fun getCoinsList(): List<CoinPresentation>

    suspend fun saveCoinsToCache(coins: List<CoinPresentation>)
}