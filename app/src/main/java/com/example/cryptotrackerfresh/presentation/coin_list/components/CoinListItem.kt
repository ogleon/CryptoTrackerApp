package com.example.cryptotrackerfresh.presentation.coin_list.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import com.example.cryptotrackerfresh.R
import com.example.cryptotrackerfresh.common.Constants
import com.example.cryptotrackerfresh.domain.model.Coin
import kotlinx.coroutines.ExperimentalCoroutinesApi

@RequiresApi(Build.VERSION_CODES.Q)
@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@Composable
fun CoinListItem(
    coin: Coin,
    onClick: (Coin) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { onClick.invoke(coin) }
            .wrapContentHeight(),
        shape = RoundedCornerShape(6.dp),
        backgroundColor = colorResource(id = R.color.backgroundCard),
        elevation = 7.dp
    )
    {
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
                    .wrapContentSize()
                    .padding(end = 4.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center) {

                CompositionLocalProvider(LocalImageLoader.provides(imageLoader)) {
                    val painter =
                        rememberImagePainter(Constants.IMG_URL + coin.name.lowercase() + "-" + coin.symbol.lowercase() + "-logo.svg?v=002")
                    Image(
                        painter = painter,
                        contentDescription = "SVG Picture",
                        modifier = Modifier
                            .size(64.dp)
                            .wrapContentSize(),
                        contentScale = ContentScale.Fit,
                        alignment = Alignment.CenterStart

                    )
                }
            }

            Column(modifier = Modifier.fillMaxWidth()) {

                Row {
                    Column(modifier = Modifier
                        .wrapContentSize(),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center) {
                        Text(
                            text = coin.symbol,
                            modifier = Modifier
                                .padding(start = 4.dp),
                            fontWeight = FontWeight.Bold)
                    }
                    Column(modifier = Modifier
                        .wrapContentSize(),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center) {
                        Text(
                            text = coin.name,
                            modifier = Modifier
                                .padding(start = 4.dp),
                            fontWeight = FontWeight.Bold,
                        color = Color.LightGray)
                    }
                    Column(modifier = Modifier
                        .fillMaxWidth(),
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Center) {
                        Text(text = "$" + coin.price,
                            modifier = Modifier
                                .padding(end = 4.dp))
                    }
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 3.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {

                    if (coin.percentChange1h.contains("-")) {
                        Text(text = "1h: ")
                        Text(text = coin.percentChange1h.plus("%"),
                            modifier = Modifier
                                .padding(end = 4.dp),
                            color = Color.Red)
                    } else {
                        Text(text = "1h: ")
                        Text(text = coin.percentChange1h.plus("%"),
                            modifier = Modifier
                                .padding(end = 4.dp),
                            color = Color.Green)
                    }

                    if (coin.percentChange24h.contains("-")) {
                        Text(text = "24h: ")

                        Text(text = coin.percentChange24h.plus("%"),
                            modifier = Modifier
                                .padding(end = 4.dp),
                            color = Color.Red)
                    } else {
                        Text(text = "24h: ")

                        Text(text = coin.percentChange24h.plus("%"),
                            modifier = Modifier
                                .padding(end = 4.dp),
                            color = Color.Green)
                    }

                    if (coin.percentChange7d.contains("-")) {

                        Text(text = "7d: ")

                        Text(text = coin.percentChange7d.plus("%"),
                            modifier = Modifier
                                .padding(end = 4.dp),
                            color = Color.Red)
                    } else {
                        Text(text = "7d: ")

                        Text(text = coin.percentChange7d.plus("%"),
                            modifier = Modifier
                                .padding(end = 4.dp),
                            color = Color.Green)
                    }

                }
            }

        }
    }
}


