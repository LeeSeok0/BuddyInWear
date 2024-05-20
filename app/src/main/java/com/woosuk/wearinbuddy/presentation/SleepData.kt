package com.woosuk.wearinbuddy.presentation


import java.time.LocalTime

data class SleepData(
    val id: String,
    val sleepStartTime: LocalTime,
    val sleepEndTime: LocalTime,
    val sleepDuration: Int
)
