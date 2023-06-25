package com.example.unittest.domain.cache.usecase

import com.example.unittest.domain.cache.core.CoinsCacheRepository
import com.example.unittest.domain.core.model.CoinPresentation

interface GetCoinsFromCacheUseCase {
    suspend fun execute(): List<CoinPresentation>
    class Base(private val repository: CoinsCacheRepository) : GetCoinsFromCacheUseCase {
        override suspend fun execute(): List<CoinPresentation> = repository.getCoinsList()
    }
}