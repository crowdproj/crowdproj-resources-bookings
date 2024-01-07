package com.crowdproj.resources.bookings.common.models

import kotlin.jvm.JvmInline

@JvmInline
value class UserIdCommon(private val id: String) {
    fun asString(): String = id

    companion object {
        val NONE = UserIdCommon("")
    }
}