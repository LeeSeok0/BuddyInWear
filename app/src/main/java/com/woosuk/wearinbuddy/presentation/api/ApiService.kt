package com.woosuk.wearinbuddy.presentation.api

import com.woosuk.wearinbuddy.presentation.data.activity.ActivityRequest
import com.woosuk.wearinbuddy.presentation.data.activity.ActivityResponse
import com.woosuk.wearinbuddy.presentation.data.sleep.SleepRequest
import com.woosuk.wearinbuddy.presentation.data.sleep.SleepResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class IdRequest(val id: Int)

interface ApiService {
    @POST("/sleep")
    fun getSleepData(@Body request: SleepRequest): Call<List<SleepResponse>>
    @POST("/activity")
    fun getActivityData(@Body request: ActivityRequest): Call<List<ActivityResponse>>
}