package com.woosuk.wearinbuddy.presentation.data.sleep

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

data class SleepResponse(
    val hashcode: String,
    val sleep_duration: Double,
    val sleep_bedtime_start: LocalTime,
    val sleep_bedtime_end: LocalTime
)
