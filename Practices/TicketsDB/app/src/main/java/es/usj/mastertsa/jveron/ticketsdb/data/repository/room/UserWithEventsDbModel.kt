package es.usj.mastertsa.jveron.ticketsdb.data.repository.room

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class UserWithEventsDbModel(
    @Embedded val user: UserDbModel,
    @Relation(
        parentColumn = "user_email",
        entityColumn = "event_id",
        associateBy = Junction(UserEventCrossRefDbModel::class)
    )
    val events: List<EventDbModel>
)
