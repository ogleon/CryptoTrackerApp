package com.example.cryptotrackerfresh.domain.model

data class CoinDetail(
    val image: String,
    val last_updated: String,
    val id: String,
    val name: String,
    val symbol: String,
    val price: String,
    val rank: String,
    val marketCap: String,
    val circulatingSupply: String,
    val maxSupply: String,
    val totalSupply: String
)