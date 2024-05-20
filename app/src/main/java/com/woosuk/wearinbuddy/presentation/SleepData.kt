package com.woosuk.wearinbuddy.presentation


import java.time.LocalTime

data class SleepData(
    val id: Int,
    val sleepDuration: Double,
    val sleepBedtimeStart: LocalTime,
    val sleepBedtimeEnd: LocalTime
)

data class SleepRequest(val id: Int)