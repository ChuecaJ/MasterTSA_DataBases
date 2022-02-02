package es.usj.mastertsa.jveron.ticketsdb.data.repository.room

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class EventWithUsersDbModel(
    @Embedded val event: EventDbModel,
    @Relation(
        parentColumn = "eventId",
        entityColumn = "userId",
        associateBy = Junction(UserEventCrossRefDbModel::class)
    )
    val users: List<UserDbModel>
)
