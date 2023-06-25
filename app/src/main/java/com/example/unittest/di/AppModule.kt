package com.example.unittest.di

import com.example.unittest.BuildConfig
import com.google.gson.GsonBuilder
import com.example.unittest.domain.network.core.CoinRankingApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideCoinRankingApiService(client: OkHttpClient): CoinRankingApiService =
        Retrofit.Builder().baseUrl("https://api.coinranking.com/v2/")
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().create()
                )
            )
            .client(client)
            .build()
            .create(CoinRankingApiService::class.java)

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun providesInterceptor(): Interceptor = HttpLoggingInterceptor()
        .setLevel(
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE

            }
        )
}