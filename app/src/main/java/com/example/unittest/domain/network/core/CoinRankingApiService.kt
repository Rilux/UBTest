package com.example.unittest.domain.network.core

import com.example.unittest.domain.network.model.CoinsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface CoinRankingApiService {

    @Headers("Content-Type: application/json")
    @GET("coins")
    suspend fun getCoinsList(@Header("x-access-token") apiToken: String) : CoinsResponse


}