package com.woosuk.wearinbuddy.presentation.util

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class LocalTimeDeserializer : JsonDeserializer<LocalTime> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): LocalTime {
        return LocalTime.parse(json.asString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"))
    }
}
