package com.example.unittest.di

import android.content.Context
import androidx.room.Room
import com.example.unittest.data.cache.core.AppDatabase
import com.example.unittest.data.cache.core.CoinsCacheRepositoryImpl
import com.example.unittest.data.cache.core.CoinsDao
import com.example.unittest.data.network.core.CoinRankingRepositoryImpl
import com.example.unittest.domain.cache.core.CoinsCacheRepository
import com.example.unittest.domain.cache.usecase.GetCoinsFromCacheUseCase
import com.example.unittest.domain.cache.usecase.SaveCoinsToCacheUseCase
import com.example.unittest.domain.core.usecase.FetchCoinsListUseCase
import com.example.unittest.domain.network.core.CoinRankingApiService
import com.example.unittest.domain.network.core.CoinRankingRepository
import com.example.unittest.domain.network.usecase.FetchCoinListFromApiUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named


@Module
@InstallIn(ActivityRetainedComponent::class)
class CoinListFeatureModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "CoinsDatabase"
        ).build()
    }

    @Provides
    fun provideCoinsDao(appDatabase: AppDatabase): CoinsDao {
        return appDatabase.coinsDao()
    }

    @Provides
    fun provideCoinRankingRepository(
        @Named("CoinRankingApiKey") apiKey: String,
        coinRankingApiService: CoinRankingApiService,
        @IoDispatcher dispatcher: CoroutineDispatcher,
    ): CoinRankingRepository =
        CoinRankingRepositoryImpl(
            token = apiKey,
            coinRankingApiService = coinRankingApiService,
            ioDispatcher = dispatcher
        )

    @Provides
    fun provideCoinsCacheRepository(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        dao: CoinsDao,
    ): CoinsCacheRepository =
        CoinsCacheRepositoryImpl(
            dao = dao,
            ioDispatcher = dispatcher
        )

    @Provides
    fun provideFetchCoinListFromApiUseCase(
        repository: CoinRankingRepository,
        saveCoinsToCacheUseCase: SaveCoinsToCacheUseCase,
    ): FetchCoinListFromApiUseCase =
        FetchCoinListFromApiUseCase.Base(
            repository = repository,
            saveCoinsToCacheUseCase = saveCoinsToCacheUseCase
        )


    @Provides
    fun provideFetchCoinsListUseCase(
        fetchCoinListFromApiUseCase: FetchCoinListFromApiUseCase,
        fetchCoinsFromCacheUseCase: GetCoinsFromCacheUseCase,
    ): FetchCoinsListUseCase =
        FetchCoinsListUseCase.Base(
            fetchCoinListFromApiUseCase = fetchCoinListFromApiUseCase,
            fetchCoinsFromCacheUseCase = fetchCoinsFromCacheUseCase
        )

    @Provides
    fun provideSaveCoinsToCacheUseCase(
        repository: CoinsCacheRepository,
    ): SaveCoinsToCacheUseCase =
        SaveCoinsToCacheUseCase.Base(
            repository = repository,
        )

    @Provides
    fun provideGetCoinsFromCacheUseCase(
        repository: CoinsCacheRepository,
    ): GetCoinsFromCacheUseCase =
        GetCoinsFromCacheUseCase.Base(
            repository = repository,
        )
}