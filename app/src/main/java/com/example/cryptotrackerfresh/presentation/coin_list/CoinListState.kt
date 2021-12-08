package com.example.cryptotrackerfresh.presentation.coin_list

import com.example.cryptotrackerfresh.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
