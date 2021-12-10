package com.example.cryptotrackerfresh.presentation.coin_list.components

import android.view.Gravity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cryptotrackerfresh.R
import com.example.cryptotrackerfresh.common.Constants
import com.example.cryptotrackerfresh.domain.model.Coin
import com.google.android.material.internal.BaselineLayout
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun CoinListItem(
    coin: Coin,
    onClick: (Coin) -> Unit
) {
    Card(
        modifier = Modifier
            .clickable { onClick.invoke(coin) }
            .fillMaxWidth()
            .padding(6.dp)
            .wrapContentHeight()
            .background(Color(R.color.backgroundCard)),
        RoundedCornerShape(3.dp),
        elevation = 8.dp
    ) {
        Row {

            Column (modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(6.dp)){

                GlideImage(
                    Constants.IMG_URL + coin.symbol.lowercase(),
                    modifier = Modifier
                        .height(64.dp)
                        .width(64.dp)
                )
            }

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    ){

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    Arrangement.SpaceAround,
                ) {
                    Text(text = coin.symbol)
                    Text(text = coin.name)
                    Text(text = coin.price)
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    Arrangement.SpaceAround,
                ) {
                    Text(text = coin.percentChange1h.plus("%"))
                    Text(text = coin.percentChange24h.plus("%"))
                    Text(text = coin.percentChange7d.plus("%"))
                }

            }
        }

    }

}