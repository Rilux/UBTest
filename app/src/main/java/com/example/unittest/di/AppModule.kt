package com.example.unittest.di

import com.example.unittest.BuildConfig
import com.example.unittest.domain.network.core.CoinRankingApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideCoinRankingApiService(client: OkHttpClient): CoinRankingApiService =
        Retrofit.Builder().baseUrl("https://api.coinranking.com/v2/")
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
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

    @Provides
    @Singleton
    @Named("CoinRankingApiKey")
    fun provideApiKey(): String = BuildConfig.API_KEY

}