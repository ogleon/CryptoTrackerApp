package com.example.cryptotrackerfresh.presentation.coin_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import com.example.cryptotrackerfresh.common.Constants

@ExperimentalCoilApi
@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        state.coin?.let { coin ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                item {
                    Box(modifier = Modifier
                        .padding(8.dp)
                        .background(Color.Yellow)) {
                        Row(
                            modifier = Modifier
                                .wrapContentHeight()
                                .fillMaxWidth()
                                .padding(6.dp),
                            Arrangement.SpaceBetween,
                        ) {
                            val imageLoader = ImageLoader.Builder(LocalContext.current)
                                .componentRegistry {
                                    add(SvgDecoder(LocalContext.current))
                                }
                                .build()

                            Column(
                                modifier = Modifier
                                    .wrapContentSize(),
                                horizontalAlignment = Alignment.Start) {

                                Row(verticalAlignment = Alignment.CenterVertically
                                ) {
                                    CompositionLocalProvider(LocalImageLoader.provides(imageLoader)) {
                                        val painter =
                                            rememberImagePainter(Constants.IMG_URL + coin.symbol.lowercase() + ".svg")

                                        Image(
                                            painter = painter,
                                            contentDescription = "SVG Picture",
                                            modifier = Modifier
                                                .size(64.dp),
                                            alignment = Alignment.CenterStart,
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                                    Column {
                                        Text(
                                            text = coin.symbol,
                                            modifier = Modifier.padding(start = 6.dp),
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(text = coin.name,
                                            modifier = Modifier.padding(start = 6.dp),
                                            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                                        )
                                    }
                                }

                            }

                            Column(modifier = Modifier
                                .wrapContentSize(),
                                horizontalAlignment = Alignment.End) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(text = "$" + coin.price)
                                }

                            }
                        }

                        Row(modifier = Modifier
                            .padding(3.dp)
                            .background(Color.LightGray)) {

                        }
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
