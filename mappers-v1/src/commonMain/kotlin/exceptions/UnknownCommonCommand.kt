package com.crowdproj.resources.bookings.mappers.v1.exceptions

import com.crowdproj.resources.bookings.common.models.CommandCommon

class UnknownCommonCommand(command: CommandCommon) :Throwable("Wrong command $command at mapping toTransport stage") {
}