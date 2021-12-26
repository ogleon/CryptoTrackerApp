package com.example.cryptotrackerfresh.presentation.coin_list

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.example.cryptotrackerfresh.R
import com.example.cryptotrackerfresh.presentation.Screen
import com.example.cryptotrackerfresh.presentation.coin_list.components.CoinListItem
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalAnimationApi
@ExperimentalCoroutinesApi
@ExperimentalCoilApi
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    val (value, onValueChange) = remember { mutableStateOf("") }

    Box(modifier = Modifier
        .background(colorResource(id = R.color.background))
        .fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize()) {

            Row(modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()) {

                TextField(
                    value = value,
                    singleLine = true,
                    onValueChange = onValueChange,
                    textStyle = TextStyle(fontSize = 17.sp),
                    leadingIcon = {
                        Icon(Icons.Filled.Search,
                            null,
                            tint = colorResource(id = R.color.gray))
                    },
                    trailingIcon = {
                        IconButton(onClick = {

                            Log.d("tot", "You just clicked")
                            Log.d("coin", value)

                            val coinSearchEntry = state.coins.firstOrNull { coin ->
                                Log.d("coinForEach", coin.symbol)
                                coin.name == value.replaceFirstChar { it.uppercaseChar() }.trim() || coin.symbol == value.uppercase().trim()
                            }

                            if (coinSearchEntry != null) {
                                navController.navigate(Screen.CoinDetailScreen.route + "/${coinSearchEntry.id}")
                            }


                        }) {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = ""
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            // navController.navigate(Screen.CoinDetailScreen.route + "/${coin.id}")
                            Log.d("clickedSearch", "clicked")
                        }
                    ),
                    modifier = Modifier
                        .height(80.dp)
                        .padding(8.dp)
                        .fillMaxWidth()
                        .background(colorResource(id = R.color.backgroundCard),
                            RoundedCornerShape(16.dp)),
                    placeholder = { Text(text = "Search coin.") },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        backgroundColor = Color.Transparent,
                        cursorColor = Color.DarkGray
                    ))
            }
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .background(
                    colorResource(id = R.color.background),
                )) {
                this.items(state.coins) { coin ->
                    CoinListItem(
                        coin = coin,
                        onClick = {
                            navController.navigate(Screen.CoinDetailScreen.route + "/${coin.id}")
                        }
                    )
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
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

}
