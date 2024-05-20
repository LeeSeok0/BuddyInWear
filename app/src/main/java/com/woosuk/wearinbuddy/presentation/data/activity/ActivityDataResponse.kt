package com.woosuk.wearinbuddy.presentation.data.activity

import java.time.LocalTime


data class ActivityResponse(
    val id: Int,
    val steps: Int,
    val calories: Double
)
