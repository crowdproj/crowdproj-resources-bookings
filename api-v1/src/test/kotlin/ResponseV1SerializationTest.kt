package com.crowdproj.resources.bookings.api.v1

import com.crowdproj.resources.bookings.api.v1.models.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ResponseV1SerializationTest {
    private val response = TimeslotCreateResponse(
        requestId = "123",
        timeslot = TimeslotResponseObject(
            userId = "123",
            resourceId = "123",
            startTime = "2022-01-01T00:00:00.000Z",
            endTime = "2023-01-01T00:00:00.000Z",
            bookingStatus = TimeslotBookingStatus.FREE
        )
    )

    @Test
    fun serialize() {
        val json = apiV1Mapper.writeValueAsString(response)

        assertContains(json, Regex("\"bookingStatus\":\\s*\"free\""))
        assertContains(json, Regex("\"userId\":\\s*\"123\""))
        assertContains(json, Regex("\"resourceId\":\\s*\"123\""))
        assertContains(json, Regex("\"startTime\":\\s*\"2022-01-01T00:00:00.000Z\""))
        assertContains(json, Regex("\"endTime\":\\s*\"2023-01-01T00:00:00.000Z\""))
    }

    @Test
    fun deserialize() {
        val json = apiV1Mapper.writeValueAsString(response)
        val obj = apiV1Mapper.readValue(json, IResponse::class.java) as TimeslotCreateResponse

        assertEquals(response, obj)
    }
}
