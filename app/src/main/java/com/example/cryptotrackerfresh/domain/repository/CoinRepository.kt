package com.example.cryptotrackerfresh.domain.repository

import com.example.cryptotrackerfresh.data.remote.dto.TickerEntity

interface CoinRepository {
    suspend fun getCoins(): List<TickerEntity>
    suspend fun getCoinById(coinId: String): TickerEntity
}