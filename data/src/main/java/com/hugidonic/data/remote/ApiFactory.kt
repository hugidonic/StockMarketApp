package com.hugidonic.data.remote

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class ApiFactory {

    val stockApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create<StockApiService>()

    companion object {
        const val API_KEY = "8M5C2REM9T04HRV9"
        const val BASE_URL = "https://alphavantage.co/"
    }
}