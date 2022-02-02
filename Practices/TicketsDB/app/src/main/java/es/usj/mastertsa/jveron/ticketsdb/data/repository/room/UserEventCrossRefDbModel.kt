package es.usj.mastertsa.jveron.ticketsdb.data.repository.room

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

@Entity(primaryKeys = ["id", "eventId"])
data class UserEventCrossRefDbModel (
    val userId: Int,
    val eventId: Int
)