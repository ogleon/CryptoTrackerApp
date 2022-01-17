package com.example.cryptotrackerfresh.data.remote.dto

data class CoinEntity(
    val id: String,
    val is_active: Boolean,
    val is_new: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)