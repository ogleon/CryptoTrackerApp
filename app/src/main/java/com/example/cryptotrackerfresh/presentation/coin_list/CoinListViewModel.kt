package com.example.cryptotrackerfresh.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.cryptotrackerfresh.common.Resource
import com.example.cryptotrackerfresh.data.remote.CoinApiService
import com.example.cryptotrackerfresh.domain.use_case.get_coin.GetCoinUseCase
import com.example.cryptotrackerfresh.domain.use_case.get_coins.GetCoinsUseCase
import com.example.cryptotrackerfresh.presentation.Screen
import com.example.cryptotrackerfresh.presentation.coin_detail.CoinDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val getCoinUseCase: GetCoinUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    private val _stateCoinSearch = mutableStateOf(CoinDetailState())
    val stateCoinSearch: State<CoinDetailState> = _stateCoinSearch

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CoinListState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CoinListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

   suspend fun goToCoinDetailScreen(navController: NavController, coinApiService : CoinApiService, value : String) {

        val coin = coinApiService.getSearches(value)

        if (coin.id.isEmpty())
            navController.navigate(Screen.CoinDetailScreen.route + "/${coin.id}")
    }
}