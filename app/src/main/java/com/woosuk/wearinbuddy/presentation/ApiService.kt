package com.woosuk.wearinbuddy.presentation

import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("sleep")
    suspend fun getSleepData(@Query("username") username: String): List<SleepDataResponse>
}