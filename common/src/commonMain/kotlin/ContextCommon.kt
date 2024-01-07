package com.crowdproj.resources.bookings.common

import com.crowdproj.resources.bookings.common.models.*
import com.crowdproj.resources.bookings.common.stubs.StubsCommon
import kotlinx.datetime.Instant


data class ContextCommon(
    var command: CommandCommon = CommandCommon.NONE,
    var state: StateCommon = StateCommon.NONE,
    val errors: MutableList<ErrorCommon> = mutableListOf(),

    var workMode: WorkModeCommon = WorkModeCommon.PROD,
    var stubCase: StubsCommon = StubsCommon.NONE,

    var requestId: RequestIdCommon = RequestIdCommon.NONE,
    var timeStart: Instant = Instant.NONE,
    var timeslotRequest: TimeslotCommon = TimeslotCommon(),
    var timeslotFilterRequest: TimeslotFilterCommon = TimeslotFilterCommon(),
    var timeslotResponse: TimeslotCommon = TimeslotCommon(),
    var timeslotsResponse: MutableList<TimeslotCommon> = mutableListOf(),
    )