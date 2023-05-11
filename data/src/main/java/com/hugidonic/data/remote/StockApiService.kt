package com.hugidonic.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApiService {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query("apiKey") apiKey: String
    ): ResponseBody

    companion object {
        const val API_KEY = "8M5C2REM9T04HRV9"
        const val BASE_URL = "https://alphavantage.co/"
    }

}