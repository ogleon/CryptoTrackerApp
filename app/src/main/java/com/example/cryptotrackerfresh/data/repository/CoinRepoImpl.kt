package com.example.cryptotrackerfresh.data.repository

import com.example.cryptotrackerfresh.data.remote.CoinApiService
import com.example.cryptotrackerfresh.data.remote.dto.TickerEntity
import com.example.cryptotrackerfresh.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepoImpl @Inject constructor(
    private val api: CoinApiService
) : CoinRepository {

    override suspend fun getCoins(): List<TickerEntity> {
        return api.getTickers("USD")
    }

    override suspend fun getCoinById(coinId: String): TickerEntity {
        return api.getTicker(coinId, "USD")
    }
}