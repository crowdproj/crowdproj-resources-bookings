## Entities

#### data class TimeSlot
    val id: Long,
    val ownerId: Long
    val resourceID: Long,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val bookingStatus: String = free

- id - unique identifier of the timeslot.
- ownerId - unique identifier of the user who create timeslot.
- resourceID - unique identifier of the resource.
- startTime and endTime - temporal markers indicating the start and end of the timeslot.
- bookingStatus - a variable indicating whether the timeslot is booked.

## API
1. CRUDS(Create, Read, Update, Delete, Search)
