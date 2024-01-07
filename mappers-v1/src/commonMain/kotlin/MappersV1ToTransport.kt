package com.crowdproj.resources.bookings.mappers.v1

import com.crowdproj.resources.bookings.api.v1.models.*
import com.crowdproj.resources.bookings.common.ContextCommon
import com.crowdproj.resources.bookings.common.models.*
import com.crowdproj.resources.bookings.mappers.v1.exceptions.UnknownCommonCommand

fun ContextCommon.toTransportTimeslot(): IResponse = when(val cmd = command) {
    CommandCommon.CREATE -> toTransportCreate()
    CommandCommon.READ -> toTransportRead()
    CommandCommon.UPDATE -> toTransportUpdate()
    CommandCommon.DELETE -> toTransportDelete()
    CommandCommon.SEARCH -> toTransportSearch()
    else -> throw UnknownCommonCommand(cmd)
}

fun ContextCommon.toTransportCreate() = TimeslotCreateResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == StateCommon.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    timeslot = timeslotResponse.toTransportTimeslot(),
)

fun ContextCommon.toTransportRead() = TimeslotReadResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == StateCommon.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    timeslot = timeslotResponse.toTransportTimeslot(),
)

fun ContextCommon.toTransportUpdate() = TimeslotUpdateResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == StateCommon.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    timeslot = timeslotResponse.toTransportTimeslot(),
)

fun ContextCommon.toTransportDelete() = TimeslotDeleteResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == StateCommon.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    timeslot = timeslotResponse.toTransportTimeslot(),
)

fun ContextCommon.toTransportSearch() = TimeslotSearchResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == StateCommon.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    timeslots = timeslotsResponse.toTransportTimeslot(),
)

fun  List<TimeslotCommon>.toTransportTimeslot(): List<TimeslotResponseObject>? = this
    .map { it.toTransportTimeslot() }
    .toList()
    .takeIf { it.isNotEmpty() }

fun ContextCommon.toTransportInit() = TimeslotInitResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == StateCommon.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
)

private fun TimeslotCommon.toTransportTimeslot(): TimeslotResponseObject = TimeslotResponseObject(
    id = id.takeIf { it != TimeslotIdCommon.NONE }?.asString(),
    ownerId = ownerId.takeIf { it != UserIdCommon.NONE }?.asString(),
    resourceId = resourceId.takeIf { it != ResourceIdCommon.NONE }?.asString(),
    startTime = startTime.toString(),
    endTime = endTime.toString(),
    bookingStatus = bookingStatus.toTransportTimeslot(),
    permissions = permissionsClient.toTransportTimeslot(),
)
private fun BookingStatusCommon.toTransportTimeslot() = when (this) {
    BookingStatusCommon.BOOKED -> TimeslotBookingStatus.BOOKED
    BookingStatusCommon.FREE -> TimeslotBookingStatus.FREE
    else -> TimeslotBookingStatus.FREE
}
private fun Set<TimeslotPermissionClientCommon>.toTransportTimeslot(): Set<TimeslotPermissions>? = this
    .map { it.toTransportTimeslot() }
    .toSet()
    .takeIf { it.isNotEmpty() }

private fun TimeslotPermissionClientCommon.toTransportTimeslot() = when (this) {
    TimeslotPermissionClientCommon.READ -> TimeslotPermissions.READ
    TimeslotPermissionClientCommon.UPDATE -> TimeslotPermissions.UPDATE
    TimeslotPermissionClientCommon.DELETE -> TimeslotPermissions.DELETE
}

private fun List<ErrorCommon>.toTransportErrors(): List<Error>? = this
    .map { it.toTransportTimeslot() }
    .toList()
    .takeIf { it.isNotEmpty() }

private fun  ErrorCommon.toTransportTimeslot() = Error(
    code = code.takeIf { it.isNotBlank() },
    group = group.takeIf { it.isNotBlank() },
    field = field.takeIf { it.isNotBlank() },
    message = message.takeIf { it.isNotBlank() },
)