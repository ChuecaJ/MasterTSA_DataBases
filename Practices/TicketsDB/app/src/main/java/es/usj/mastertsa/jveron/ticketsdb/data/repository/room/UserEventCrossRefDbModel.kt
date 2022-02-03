package es.usj.mastertsa.jveron.ticketsdb.data.repository.room

import androidx.room.Entity

const val CROSS_TABLE_NAME = "cross"

@Entity(tableName = CROSS_TABLE_NAME, primaryKeys = ["user_email", "event_id"])
data class UserEventCrossRefDbModel (
    val user_email: String,
    val event_id: Int
)