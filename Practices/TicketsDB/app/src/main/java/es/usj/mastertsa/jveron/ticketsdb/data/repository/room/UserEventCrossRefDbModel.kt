package es.usj.mastertsa.jveron.ticketsdb.data.repository.room

import androidx.room.Entity

@Entity(primaryKeys = ["id", "eventId"])
data class UserEventCrossRefDbModel (
    val userId: Int,
    val eventId: Int
)