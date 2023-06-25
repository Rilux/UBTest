package com.example.unittest.domain.network.core

import com.example.unittest.domain.core.model.CoinPresentation
import com.example.unittest.domain.network.model.CoinsResponse
import com.example.unittest.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CoinRankingRepository {

    suspend fun getCoinItems() : Flow<Resource<List<CoinPresentation>>>
}