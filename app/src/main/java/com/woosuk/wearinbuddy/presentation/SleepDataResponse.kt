package com.woosuk.wearinbuddy.presentation

import java.time.Instant
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class SleepDataResponse(
    val sleepDuration: Int,
    val createDate: Long
) {
    fun toSleepData(): SleepData {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        val createTime = Instant.ofEpochSecond(createDate).atZone(ZoneId.systemDefault()).toLocalTime()
        // Here assuming 8 hours of sleep for example purpose
        val sleepStartTime = createTime.minusHours(sleepDuration.toLong())
        val sleepEndTime = createTime

        return SleepData(
            id = "0",
            sleepStartTime = sleepStartTime,
            sleepEndTime = sleepEndTime,
            sleepDuration = sleepDuration
        )
    }
}
