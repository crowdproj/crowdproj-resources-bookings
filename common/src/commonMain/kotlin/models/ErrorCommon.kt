package com.crowdproj.resources.bookings.common.models

data class ErrorCommon(
    val code: String = "",
    val group: String = "",
    val field: String = "",
    val message: String = "",
    val exception: Throwable? = null
)
