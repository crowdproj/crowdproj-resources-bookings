package com.crowdproj.resources.bookings.common.models

import kotlin.jvm.JvmInline

@JvmInline
value class TimeslotIdCommon(private val id: String) {
    fun asString(): String = id
    companion object {
        val NONE = TimeslotIdCommon("")
    }
}