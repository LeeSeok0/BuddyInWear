package com.woosuk.wearinbuddy.presentation.ViewModel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.woosuk.wearinbuddy.presentation.ApiService
import com.woosuk.wearinbuddy.presentation.SleepData
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalTime

class SleepViewModel(application: Application) : AndroidViewModel(application) {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://your-api-base-url.com/") // Replace with your API base URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)

    private val _sleepData = mutableStateOf(SleepData("0", LocalTime.MIDNIGHT, LocalTime.MIDNIGHT, 0))
    val sleepData: State<SleepData> get() = _sleepData

    fun fetchSleepData(username: String) {
        viewModelScope.launch {
            try {
                val response = apiService.getSleepData(username)
                if (response.isNotEmpty()) {
                    _sleepData.value = response[0].toSleepData() // Assuming we take the first record
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}