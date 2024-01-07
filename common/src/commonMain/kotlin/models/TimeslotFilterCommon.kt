package com.crowdproj.resources.bookings.common.models

data class TimeslotFilterCommon(
    var searchString: String = "",
    var ownerId: UserIdCommon = UserIdCommon.NONE,
    var resourceId: ResourceIdCommon = ResourceIdCommon.NONE,
)
