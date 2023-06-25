package com.example.unittest.domain.cache.usecase

import com.example.unittest.domain.cache.core.CoinsCacheRepository
import com.example.unittest.domain.core.model.CoinPresentation

interface SaveCoinsToCacheUseCase {
    suspend fun execute(coins: List<CoinPresentation>)

    class Base(private val repository: CoinsCacheRepository) : SaveCoinsToCacheUseCase {
        override suspend fun execute(coins: List<CoinPresentation>) =
            repository.saveCoinsToCache(coins)
    }
}