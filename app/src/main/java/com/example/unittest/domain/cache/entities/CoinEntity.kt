package com.example.unittest.domain.cache.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coin_entity")
data class CoinEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val uuid: String,
    val symbol: String,
    val name: String,
    val color: String?,
    @ColumnInfo(name = "icon_url")
    val iconUrl: String,
    val price: String,
)
