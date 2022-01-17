package com.example.cryptotrackerfresh.data.remote.dto

import com.example.cryptotrackerfresh.common.Constants
import com.example.cryptotrackerfresh.domain.model.Coin
import com.example.cryptotrackerfresh.domain.model.CoinDetail

data class TickerEntity(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int = -1,
    val circulating_supply: Long = 0,
    val total_supply: Long = 0,
    val max_supply: Long = 0,
    val beta_value: Double = 0.0,
    val last_updated: String? = "",
    var quotes: Map<String, QuoteEntity>? = emptyMap(),
    var tags: List<String>? = emptyList()
)

fun TickerEntity.toCoin(): Coin {

    return Coin(
        image = (Constants.IMG_URL + name.replace(" ", "-") + "-" + symbol + "-logo.svg?v=018").lowercase(),
        id = id,
        name = name,
        symbol = symbol,
        price = quotes?.get("USD")?.price.toString(),
        percentChange1h = quotes?.get("USD")?.percent_change_1h.toString(),
        percentChange24h = quotes?.get("USD")?.percent_change_24h.toString(),
        percentChange7d = quotes?.get("USD")?.percent_change_7d.toString(),
    )
}

fun TickerEntity.toCoinDetail(): CoinDetail {
    return CoinDetail(

        image = (Constants.IMG_URL + name.replace(" ", "-") + "-" + symbol + "-logo.svg?v=018").lowercase(),
        last_updated = last_updated.toString(),
        id = id,
        name = name,
        symbol = symbol,
        price = quotes?.get("USD")?.price.toString(),
        rank = rank.toString(),
        marketCap = quotes?.get("USD")?.market_cap.toString(),
        circulatingSupply = circulating_supply.toString(),
        maxSupply = max_supply.toString(),
        totalSupply = total_supply.toString()
    )
}