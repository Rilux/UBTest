package com.example.unittest.data.network.core

import com.example.unittest.di.IoDispatcher
import com.example.unittest.domain.core.model.CoinPresentation
import com.example.unittest.domain.network.core.CoinRankingApiService
import com.example.unittest.domain.network.core.CoinRankingRepository
import com.example.unittest.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CoinRankingRepositoryImpl(
    private val token: String,
    private val coinRankingApiService: CoinRankingApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : CoinRankingRepository {

    override suspend fun getCoinItems(): Flow<Resource<List<CoinPresentation>>> {
        return flow {
            val result = coinRankingApiService.getCoinsList(token)
            if (result.status == "success") {
                emit(Resource.success(result.data.coins.map {
                    it.toCoinPresentation()
                }))
            } else {
                emit(Resource.error("", null))
            }
        }
            .catch { emit(Resource.error(it.message.toString(), null)) }
            .flowOn(ioDispatcher)
    }
}