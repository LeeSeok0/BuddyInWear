package com.woosuk.wearinbuddy.presentation.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.woosuk.wearinbuddy.presentation.util.LocalTimeDeserializer
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalTime
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private const val BASE_URL = "http://43.202.62.173:8081/" // 예제 URL

    private val gson: Gson = GsonBuilder()
        .registerTypeAdapter(LocalTime::class.java, LocalTimeDeserializer())
        .create()

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService::class.java)
    }
}
