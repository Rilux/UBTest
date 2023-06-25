package com.example.unittest.data.cache.core

import com.example.unittest.di.IoDispatcher
import com.example.unittest.domain.cache.core.CoinsCacheRepository
import com.example.unittest.domain.core.model.CoinPresentation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class CoinsCacheRepositoryImpl(
    private val dao: CoinsDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : CoinsCacheRepository {

    override suspend fun getCoinsList(): List<CoinPresentation> = coroutineScope {
        withContext(ioDispatcher) {
            dao.getCoinsList().map { it.toCoinPresentation() }
        }
    }

    override suspend fun saveCoinsToCache(coins: List<CoinPresentation>) {
        withContext(ioDispatcher) {
            dao.insertCoins(coins = coins.map { it.toCoinEntity() })
        }
    }
}