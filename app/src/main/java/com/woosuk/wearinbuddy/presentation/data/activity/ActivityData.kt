package com.woosuk.wearinbuddy.presentation.data.activity


import java.time.LocalTime

data class ActivityData(
    val hashcode: String,
    val steps: Int,
    val calories: Double
)

data class ActivityRequest(val hashcode: String)