package com.woosuk.wearinbuddy.presentation.api

import com.woosuk.wearinbuddy.presentation.data.SleepRequest
import com.woosuk.wearinbuddy.presentation.data.SleepResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class IdRequest(val id: Int)

interface ApiService {
    @POST("/sleep")
    fun getSleepData(@Body request: SleepRequest): Call<List<SleepResponse>>
}