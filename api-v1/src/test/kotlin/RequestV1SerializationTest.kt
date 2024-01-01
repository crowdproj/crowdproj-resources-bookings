package com.crowdproj.resources.bookings.api.v1

import com.crowdproj.resources.bookings.api.v1.models.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class RequestV1SerializationTest {
    private val request = TimeslotCreateRequest(
        requestId = "123",
        debug = TimeslotDebug(
            mode = TimeslotRequestDebugMode.STUB,
            stub = TimeslotRequestDebugStubs.BAD_TITLE
        ),
        timeslot = TimeslotCreateObject(
            userId = "123",
            resourceId = "123",
            startTime = "2022-01-01T00:00:00.000Z",
            endTime = "2023-01-01T00:00:00.000Z",
            isBooked = "false"
        )
    )

    @Test
    fun serialize() {
        val json = apiV1Mapper.writeValueAsString(request)

        assertContains(json, Regex("\"isBooked\":\\s*\"false\""))
        assertContains(json, Regex("\"userId\":\\s*\"123\""))
        assertContains(json, Regex("\"resourceId\":\\s*\"123\""))
        assertContains(json, Regex("\"startTime\":\\s*\"2022-01-01T00:00:00.000Z\""))
        assertContains(json, Regex("\"endTime\":\\s*\"2023-01-01T00:00:00.000Z\""))
        assertContains(json, Regex("\"mode\":\\s*\"stub\""))
        assertContains(json, Regex("\"stub\":\\s*\"badTitle\""))
        assertContains(json, Regex("\"requestType\":\\s*\"create\""))
    }

    @Test
    fun deserialize() {
        val json = apiV1Mapper.writeValueAsString(request)
        val obj = apiV1Mapper.readValue(json, IRequest::class.java) as TimeslotCreateRequest

        assertEquals(request, obj)
    }

    @Test
    fun deserializeNaked() {
        val jsonString = """
            {"requestId": "123"}
        """.trimIndent()
        val obj = apiV1Mapper.readValue(jsonString, TimeslotCreateRequest::class.java)

        assertEquals("123", obj.requestId)
    }
}
