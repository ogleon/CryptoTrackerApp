package com.example.cryptotrackerfresh.presentation.coin_list.components

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
@SuppressLint("UnsafeDynamicallyLoadedCode")
@Composable
fun CoinListItem(
    coin: Coin,
    onClick: (Coin) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(7.dp)
            .clickable { onClick.invoke(coin) }
            .wrapContentHeight()
            .clip(RoundedCornerShape(6.dp))
            .background(Color(R.color.backgroundCard)),
        elevation = 9.dp
    ) {
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
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = coin.name,
                            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                        )
                    }
                }

            }

            Column(modifier = Modifier
                .fillMaxHeight()
                .padding(3.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween) {
                Row {
                    Text(text = "$" + coin.price, fontWeight = FontWeight.Bold)
                }
                Row {
                    Text(text = coin.percentChange1h.plus("%"))
                    Text(text = coin.percentChange24h.plus("%"))
                    Text(text = coin.percentChange7d.plus("%"))
                }
            }

        }


    }

}