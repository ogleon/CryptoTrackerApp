package com.example.cryptotrackerfresh.data.remote

import com.example.cryptotrackerfresh.data.remote.dto.SearchResponse
import com.example.cryptotrackerfresh.data.remote.dto.TickerEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinApiService {

    @GET("tickers/{id}/")
    suspend fun getTicker(
        @Path("id") id: String,
        @Query("quotes") quotes: String
    ): TickerEntity

    @GET("tickers")
    suspend fun getTickers(
        @Query("quotes") quotes: String,
        @Query("limit") limit: Int? = null
    ): List<TickerEntity>


    @GET("search/?c=currencies")
    suspend fun getSearches(@Query("q") query: String): TickerEntity
}