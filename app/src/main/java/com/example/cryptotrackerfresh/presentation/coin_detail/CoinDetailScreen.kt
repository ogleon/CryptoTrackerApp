package com.example.cryptotrackerfresh.presentation.coin_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.example.cryptotrackerfresh.R.color.background
import com.example.cryptotrackerfresh.R.color.backgroundCard

@ExperimentalCoilApi
@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    Box(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = background))) {
        state.coin?.let { coin ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                item {
                    Box(modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize()
                        .background(colorResource(id = backgroundCard))) {

                        Text(text = coin.last_updated,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                        color = Color.Cyan)




                    }

                }
            }
        }
    }
    if (state.error.isNotBlank()) {
        Text(
            text = state.error,
            color = MaterialTheme.colors.error,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        )
    }
    if (state.isLoading) {
        CircularProgressIndicator(modifier = Modifier
            .fillMaxSize())
    }
}
