package com.app.composeapplication.second_App.util

import retrofit2.http.Query


interface StockApi {

    suspend fun getlistings(
        @Query("apikey") apikey: String

    )
    companion object{
        const  val API_KEY = "Q63Y9NX3TUF587NF"
        const  val BASE_URL  = "https://www.alphavantage.co/"
    }
}