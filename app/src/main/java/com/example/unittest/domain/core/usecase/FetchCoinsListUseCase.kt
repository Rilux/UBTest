package com.example.unittest.domain.core.usecase

import com.example.unittest.domain.core.model.CoinPresentation
import com.example.unittest.utils.Resource

interface FetchCoinsListUseCase {
    suspend fun execute() : Resource<CoinPresentation>

    class Base() : FetchCoinsListUseCase {
        override suspend fun execute(): Resource<CoinPresentation> {
            TODO("Not yet implemented")
        }
    }
}