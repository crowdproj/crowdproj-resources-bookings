package com.crowdproj.resources.bookings.mappers.v1

import com.crowdproj.resources.bookings.api.v1.models.*
import com.crowdproj.resources.bookings.common.ContextCommon
import com.crowdproj.resources.bookings.common.models.*
import com.crowdproj.resources.bookings.common.stubs.StubsCommon
import com.crowdproj.resources.bookings.mappers.v1.exceptions.UnknownRequestClass
import kotlinx.datetime.Instant

fun ContextCommon.fromTransport(request: IRequest) = when(request) {
    is TimeslotCreateRequest -> fromTransport(request)
    is TimeslotReadRequest -> fromTransport(request)
    is TimeslotUpdateRequest -> fromTransport(request)
    is TimeslotDeleteRequest -> fromTransport(request)
    is TimeslotSearchRequest -> fromTransport(request)
    else -> throw UnknownRequestClass(request::class)
}

private fun String?.toTimeslotId() = this?.let { TimeslotIdCommon(it) } ?: TimeslotIdCommon.NONE
private fun String?.toTimeslotWithId() = TimeslotCommon(id = this.toTimeslotId())
private fun String?.toUserIdCommon() = this?.let { UserIdCommon(it) } ?: UserIdCommon.NONE
private fun String?.toResourceIdCommon() = this?.let { ResourceIdCommon(it) } ?: ResourceIdCommon.NONE
private fun String?.toTime() = this?.let { Instant.parse(it) } ?: Instant.parse(Long.MIN_VALUE.toString())
private fun IRequest?.requestId() = this?.requestId?.let { RequestIdCommon(it) } ?: RequestIdCommon.NONE

private fun TimeslotDebug?.transportToWorkMode(): WorkModeCommon = when(this?.mode) {
    TimeslotRequestDebugMode.PROD -> WorkModeCommon.PROD
    TimeslotRequestDebugMode.TEST -> WorkModeCommon.TEST
    TimeslotRequestDebugMode.STUB -> WorkModeCommon.STUB
    null -> WorkModeCommon.PROD
}

private fun TimeslotBookingStatus?.transportToBookingStatus(): BookingStatusCommon = when(this) {
    TimeslotBookingStatus.BOOKED -> BookingStatusCommon.BOOKED
    TimeslotBookingStatus.FREE -> BookingStatusCommon.FREE
    null -> BookingStatusCommon.FREE
}

private fun TimeslotDebug?.transportToStubCase(): StubsCommon = when (this?.stub) {
    TimeslotRequestDebugStubs.SUCCESS -> StubsCommon.SUCCESS
    TimeslotRequestDebugStubs.NOT_FOUND -> StubsCommon.NOT_FOUND
    TimeslotRequestDebugStubs.BAD_ID -> StubsCommon.BAD_ID
    TimeslotRequestDebugStubs.BAD_SEARCH_STRING -> StubsCommon.BAD_SEARCH_STRING
    TimeslotRequestDebugStubs.BAD_OWNER_ID -> StubsCommon.BAD_OWNER_ID
    TimeslotRequestDebugStubs.BAD_RESOURCE_ID -> StubsCommon.BAD_RESOURCE_ID
    TimeslotRequestDebugStubs.BAD_BOOKING_STATUS -> StubsCommon.BAD_BOOKING_STATUS
    TimeslotRequestDebugStubs.BAD_START_TIME -> StubsCommon.BAD_START_TIME
    TimeslotRequestDebugStubs.BAD_END_TIME -> StubsCommon.BAD_END_TIME
    TimeslotRequestDebugStubs.CANNOT_DELETE -> StubsCommon.CANNOT_DELETE
    null -> StubsCommon.NONE
}

fun ContextCommon.fromTransport(request: TimeslotCreateRequest) {
    command = CommandCommon.CREATE
    requestId = request.requestId()
    timeslotRequest = request.timeslot?.toInternal() ?: TimeslotCommon()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun ContextCommon.fromTransport(request: TimeslotReadRequest) {
    command = CommandCommon.READ
    requestId = request.requestId()
    timeslotRequest = request.timeslot?.id.toTimeslotWithId()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun ContextCommon.fromTransport(request: TimeslotUpdateRequest) {
    command = CommandCommon.UPDATE
    requestId = request.requestId()
    timeslotRequest = request.timeslot?.toInternal() ?: TimeslotCommon()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun ContextCommon.fromTransport(request: TimeslotDeleteRequest) {
    command = CommandCommon.DELETE
    requestId = request.requestId()
    timeslotRequest = request.timeslot?.id.toTimeslotWithId()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun ContextCommon.fromTransport(request: TimeslotSearchRequest) {
    command = CommandCommon.SEARCH
    requestId = request.requestId()
    timeslotFilterRequest = request.timeslotFilter.toInternal()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

private fun TimeslotSearchFilter?.toInternal(): TimeslotFilterCommon = TimeslotFilterCommon(
    searchString = this?.searchValue ?:""
)

private fun TimeslotCreateObject?.toInternal(): TimeslotCommon = TimeslotCommon(
    ownerId = this?.userId.toUserIdCommon(),
    resourceId = this?.resourceId.toResourceIdCommon(),
    bookingStatus = this?.bookingStatus.transportToBookingStatus(),
    startTime = Instant.parse(this?.startTime ?: Long.MIN_VALUE.toString()),
    endTime = Instant.parse(this?.endTime ?: Long.MIN_VALUE.toString()),
)

private fun TimeslotUpdateObject.toInternal(): TimeslotCommon = TimeslotCommon(
    id = this.id.toTimeslotId(),
    ownerId = this.userId.toUserIdCommon(),
    resourceId = this.resourceId.toResourceIdCommon(),
    bookingStatus = this.bookingStatus.transportToBookingStatus(),
    startTime = this.startTime.toTime(),
    endTime = this.endTime.toTime(),
)
