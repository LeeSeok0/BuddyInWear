package com.woosuk.wearinbuddy.presentation.data.activity


import java.time.LocalTime

data class ActivityData(
    val id: Int,
    val steps: Int,
    val calories: Double
)

data class ActivityRequest(val id: Int)