package com.crowdproj.resources.bookings.common.models

import com.crowdproj.resources.bookings.common.*
import kotlinx.datetime.Instant

data class TimeslotCommon (
    var id: TimeslotIdCommon = TimeslotIdCommon.NONE,
    var ownerId: UserIdCommon = UserIdCommon.NONE,
    var resourceId: ResourceIdCommon = ResourceIdCommon.NONE,
    val startTime: Instant = Instant.NONE,
    val endTime: Instant = Instant.NONE,
    val bookingStatus: BookingStatusCommon = BookingStatusCommon.NONE,
    var permissionsClient: MutableSet<TimeslotPermissionClientCommon> = mutableSetOf()
)
