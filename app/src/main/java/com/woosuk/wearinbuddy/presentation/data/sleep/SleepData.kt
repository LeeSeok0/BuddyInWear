package com.woosuk.wearinbuddy.presentation.data.sleep


import java.time.LocalTime

data class SleepData(
    val hashcode: String,
    val sleepDuration: Double,
    val sleepBedtimeStart: LocalTime,
    val sleepBedtimeEnd: LocalTime
)

data class SleepRequest(val hashcode: String)