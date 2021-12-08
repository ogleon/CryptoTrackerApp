package com.example.cryptotrackerfresh.presentation.coin_detail

import com.example.cryptotrackerfresh.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)