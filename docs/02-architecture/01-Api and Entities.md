## Entities

#### data class TimeSlot
    val id: Long,
    val userId: Long
    val resourceID: Long,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val isBooked: Boolean = false

- id - unique identifier of the timeslot.
- userId - unique identifier of the user who create timeslot.
- resourceID - unique identifier of the resource.
- startTime and endTime - temporal markers indicating the start and end of the timeslot.
- isBooked - a flag indicating whether the timeslot is booked. By default, it is set to false.

## API
1. CRUDS(Create, Read, Update, Delete, Search)
