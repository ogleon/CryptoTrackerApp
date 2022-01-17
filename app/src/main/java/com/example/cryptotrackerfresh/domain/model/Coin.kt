package com.example.cryptotrackerfresh.domain.model

data class Coin(
    val image: String = "",
    val id: String = "",
    val name: String = "",
    val symbol: String = "",
    val price: String = "",
    val percentChange1h: String = "",
    val percentChange24h: String = "",
    val percentChange7d: String = "",
)