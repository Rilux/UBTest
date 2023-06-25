package com.example.unittest.data.cache.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.unittest.domain.cache.entities.CoinEntity

@Database(
    entities = [CoinEntity::class],
    version = 1,
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun coinsDao(): CoinsDao

}