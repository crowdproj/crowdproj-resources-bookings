package com.crowdproj.resources.bookings.mappers.v1

import kotlin.test.Test

import com.crowdproj.resources.bookings.api.v1.models.*
import com.crowdproj.resources.bookings.common.ContextCommon
import com.crowdproj.resources.bookings.common.models.*
import com.crowdproj.resources.bookings.common.stubs.StubsCommon
import kotlinx.datetime.Instant
import kotlin.test.assertEquals

class MapperTest {
    @Test
    fun fromTransport() {
        val req = TimeslotCreateRequest(
            requestId = "1234",
            debug = TimeslotDebug(
                mode = TimeslotRequestDebugMode.STUB,
                stub = TimeslotRequestDebugStubs.SUCCESS
            ),
            timeslot = TimeslotCreateObject(
                startTime = "2022-01-01T00:00:00Z",
                endTime = "2022-02-01T01:00:00Z",
                resourceId = "100",
                bookingStatus = TimeslotBookingStatus.BOOKED,
            )
        )

        val context = ContextCommon()
        context.fromTransport(req)

        assertEquals(StubsCommon.SUCCESS, context.stubCase)
        assertEquals(WorkModeCommon.STUB, context.workMode)
        assertEquals(RequestIdCommon("1234"), context.requestId)
        assertEquals(Instant.parse("2022-01-01T00:00:00Z"), context.timeslotRequest.startTime)
        assertEquals(Instant.parse("2022-02-01T01:00:00Z"), context.timeslotRequest.endTime)
        assertEquals(ResourceIdCommon("100"), context.timeslotRequest.resourceId)
        assertEquals(BookingStatusCommon.BOOKED, context.timeslotRequest.bookingStatus)

    }

    @Test
    fun toTransport() {
        val context = ContextCommon(
            requestId = RequestIdCommon("1234"),
            command = CommandCommon.CREATE,
            timeslotResponse = TimeslotCommon(
                ownerId = UserIdCommon("123"),
                startTime = Instant.parse("2022-01-01T00:00:00Z"),
                endTime = Instant.parse("2022-02-01T01:00:00Z"),
                resourceId = ResourceIdCommon("100"),
                bookingStatus = BookingStatusCommon.BOOKED
            ),
            errors = mutableListOf(
                ErrorCommon(
                    code = "err",
                    group = "request",
                    field = "userId",
                    message = "wrong userId"
                )
            ),
            state = StateCommon.RUNNING
        )

        val req = context.toTransportTimeslot() as TimeslotCreateResponse

        assertEquals("1234", req.requestId)
        assertEquals("123", req.timeslot?.ownerId)
        assertEquals("100", req.timeslot?.resourceId)
        assertEquals(TimeslotBookingStatus.BOOKED, req.timeslot?.bookingStatus)
        assertEquals("2022-01-01T00:00:00Z", req.timeslot?.startTime)
        assertEquals("2022-02-01T01:00:00Z", req.timeslot?.endTime)
        assertEquals("err", req.errors?.firstOrNull()?.code)
        assertEquals("request", req.errors?.firstOrNull()?.group)
        assertEquals("userId", req.errors?.firstOrNull()?.field)
        assertEquals("wrong userId", req.errors?.firstOrNull()?.message)
    }

}
