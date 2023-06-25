package com.example.unittest.domain.core.usecase

import com.example.unittest.domain.cache.usecase.GetCoinsFromCacheUseCase
import com.example.unittest.domain.core.model.CoinPresentation
import com.example.unittest.domain.network.usecase.FetchCoinListFromApiUseCase
import com.example.unittest.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface FetchCoinsListUseCase {
    suspend fun execute(): Flow<Resource<List<CoinPresentation>>>

    class Base(
        private val fetchCoinListFromApiUseCase: FetchCoinListFromApiUseCase,
        private val fetchCoinsFromCacheUseCase: GetCoinsFromCacheUseCase,
    ) :
        FetchCoinsListUseCase {
        override suspend fun execute(): Flow<Resource<List<CoinPresentation>>> {
            fetchCoinsFromCacheUseCase.execute().let { result ->
                if (result.isEmpty()) {
                    return fetchCoinListFromApiUseCase.execute()
                } else {
                    return flow {
                        emit(Resource.success(result))
                    }
                }
            }
        }
    }
}