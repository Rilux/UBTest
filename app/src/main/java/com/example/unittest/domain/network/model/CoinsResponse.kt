package com.example.unittest.domain.network.model


import com.google.gson.annotations.SerializedName

data class CoinsResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val `data`: Data
)