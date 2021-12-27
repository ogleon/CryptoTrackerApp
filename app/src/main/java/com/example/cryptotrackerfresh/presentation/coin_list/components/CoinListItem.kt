package com.example.cryptotrackerfresh.presentation.coin_list.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import com.example.cryptotrackerfresh.R
import com.example.cryptotrackerfresh.common.Constants
import com.example.cryptotrackerfresh.domain.model.Coin
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalAnimationApi
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
            .padding(8.dp)
            .clickable { onClick.invoke(coin) }
            .wrapContentHeight(),
        shape = RoundedCornerShape(7.dp),
        backgroundColor = colorResource(id = R.color.backgroundCard),
        elevation = 8.dp
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            val imageLoader = ImageLoader.Builder(LocalContext.current)
                .componentRegistry {
                    add(SvgDecoder(LocalContext.current))
                }
                .error(R.drawable.ic_no_image_found4)
                .build()

            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(5.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center) {

                CompositionLocalProvider(LocalImageLoader.provides(imageLoader)) {
                    val painter =
                        rememberImagePainter(Constants.IMG_URL + coin.name.lowercase() + "-" + coin.symbol.lowercase() + "-logo.svg?v=002")

                    val state = painter.state
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.wrapContentSize()
                    ) {
                        this@Column.AnimatedVisibility(visible = (state is ImagePainter.State.Loading)) {
                            CircularProgressIndicator()
                        }
                        Image(
                            painter = painter,
                            contentDescription = null,
                            modifier = Modifier
                                .size(64.dp)
                                .wrapContentSize(),
                            contentScale = ContentScale.Fit,
                            alignment = Alignment.CenterStart

                        )
                    }

                }
            }

            Column(modifier = Modifier
                .wrapContentSize()
                .padding(5.dp),
                horizontalAlignment = Alignment.Start) {
                Text(
                    text = coin.symbol,
                    color = colorResource(id = R.color.black),
                    fontWeight = FontWeight.Bold)
                Text(text = coin.name,
                    color = colorResource(R.color.gray),
                    fontSize = 15.sp)
            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
                horizontalAlignment = Alignment.End) {

                Text(text = "$" + coin.price,
                    color = colorResource(id = R.color.black))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (coin.percentChange24h.contains("-")) {
                        Text(text = coin.percentChange24h.removePrefix("-").plus("%"),
                            color = colorResource(id = R.color.red))
                        Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_triangle),
                            contentDescription = null,
                            modifier = Modifier
                                .size(16.dp)
                                .padding(start = 4.dp, bottom = 2.dp)
                                .rotate(180f)
                                .wrapContentSize(),
                            colorFilter = ColorFilter.tint(colorResource(id = R.color.red)))
                    } else {
                        Text(text = coin.percentChange24h.plus("%"),
                            color = colorResource(id = R.color.green))
                        Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_triangle),
                            contentDescription = null,
                            modifier = Modifier
                                .size(16.dp)
                                .padding(start = 4.dp, bottom = 2.dp)
                                .wrapContentSize(),
                            colorFilter = ColorFilter.tint(colorResource(id = R.color.green)))
                    }
                }
            }

        }
    }
}



