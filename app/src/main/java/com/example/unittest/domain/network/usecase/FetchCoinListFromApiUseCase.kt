package com.example.unittest.domain.network.usecase

import com.example.unittest.domain.cache.usecase.SaveCoinsToCacheUseCase
import com.example.unittest.domain.core.model.CoinPresentation
import com.example.unittest.domain.network.core.CoinRankingRepository
import com.example.unittest.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

interface FetchCoinListFromApiUseCase {
    suspend fun execute(): Flow<Resource<List<CoinPresentation>>>

    class Base(
        private val repository: CoinRankingRepository,
        private val saveCoinsToCacheUseCase: SaveCoinsToCacheUseCase,
    ) : FetchCoinListFromApiUseCase {
        override suspend fun execute(): Flow<Resource<List<CoinPresentation>>> =
            repository.getCoinItems().onEach {
                it.data?.let { it1 ->
                    saveCoinsToCacheUseCase.execute(
                        it1
                    )
                }
            }
    }
}