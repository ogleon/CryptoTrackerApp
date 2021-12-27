package com.example.cryptotrackerfresh.presentation.coin_detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import com.example.cryptotrackerfresh.R
import com.example.cryptotrackerfresh.R.color.backgroundCard
import com.example.cryptotrackerfresh.common.Constants
import com.example.cryptotrackerfresh.domain.model.CoinDetail

@ExperimentalAnimationApi
@ExperimentalCoilApi
@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    Box(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = backgroundCard))) {
        state.coin?.let { coin ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                item {

                    Column(modifier = Modifier
                        .padding(10.dp),
                        verticalArrangement = Arrangement.Center) {

                        Row {
                            Text(text = coin.last_updated,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 8.dp)
                                    .wrapContentHeight(),
                                color = colorResource(id = R.color.gray),
                                textAlign = TextAlign.End,
                                fontSize = 15.sp)
                        }

                        CoinGeneralInfo(coin = coin)
                        GeneralStats(coin = coin)
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
        Box(Modifier.fillMaxSize(),
            Alignment.Center) {
            CircularProgressIndicator(Modifier.size(64.dp))
        }
    }
}

@ExperimentalCoilApi
@ExperimentalAnimationApi
@Composable
fun CoinGeneralInfo(coin: CoinDetail) {
    Row(modifier = Modifier
        .padding(2.dp)) {

        val imageLoader = ImageLoader.Builder(LocalContext.current)
            .componentRegistry {
                add(SvgDecoder(LocalContext.current))
            }
            .error(R.drawable.ic_no_image_found4)
            .build()

        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(5.dp),
            horizontalAlignment = Alignment.Start) {

            CompositionLocalProvider(LocalImageLoader.provides(imageLoader)) {
                val painter =
                    rememberImagePainter(Constants.IMG_URL + coin.name.lowercase() + "-" + coin.symbol.lowercase() + "-logo.svg?v=002")

                val state0 = painter.state
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.wrapContentSize()
                ) {
                    this@Column.AnimatedVisibility(visible = (state0 is ImagePainter.State.Loading)) {
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
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(4.dp),
            horizontalAlignment = Alignment.Start) {
            Text(
                text = coin.symbol,
                color = colorResource(id = R.color.black),
                fontWeight = FontWeight.ExtraBold)
            Text(text = coin.name,
                color = colorResource(R.color.gray),
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp)
        }
    }
}

@Composable
fun GeneralStats(coin: CoinDetail) {
    Row(
        modifier = Modifier
            .fillMaxSize()) {

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
            verticalArrangement = Arrangement.Center) {

            Text(text = "Rank", modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                colorResource(id = R.color.black),
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center)

            Text(text = coin.rank, modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                colorResource(id = R.color.gray),
                fontWeight = FontWeight.Normal,

                textAlign = TextAlign.Center)

            Text(text = "Price", modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                colorResource(id = R.color.black),
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center)

            Text(text = "$" + coin.price, modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                colorResource(id = R.color.gray),
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center)

            Text(text = "Market Cap", modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                colorResource(id = R.color.black),
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center)

            Text(text = coin.marketCap, modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                colorResource(id = R.color.gray),
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center)


            Text(text = "Max Supply", modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                colorResource(id = R.color.black),
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center)

            Text(text = coin.maxSupply, modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                colorResource(id = R.color.gray),
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center)


            Text(text = "Total Supply", modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                colorResource(id = R.color.black),
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center)

            Text(text = coin.totalSupply, modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                colorResource(id = R.color.gray),
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center)

            Text(text = "Circulating Supply", modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                colorResource(id = R.color.black),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold
            )

            Text(text = coin.circulatingSupply, modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                colorResource(id = R.color.gray),
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center)
        }

    }
}