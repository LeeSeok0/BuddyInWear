package com.woosuk.wearinbuddy.presentation

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class IdRequest(val id: Int)

interface ApiService {
    @POST("/sleep")
    fun getSleepData(@Body request: SleepRequest): Call<List<SleepResponse>>
}