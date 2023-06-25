package com.example.unittest.data.cache.core

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.unittest.domain.cache.entities.CoinEntity

@Dao
interface CoinsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoins(coins: List<CoinEntity>)

    @Query("SELECT * FROM coin_entity")
    fun getCoinsList(): List<CoinEntity>

}