package com.woosuk.wearinbuddy.presentation.data.activity

import java.time.LocalTime


data class ActivityResponse(
    val id: Int,
    val activity_steps: Int,
    val activity_cal_total: Double
)
