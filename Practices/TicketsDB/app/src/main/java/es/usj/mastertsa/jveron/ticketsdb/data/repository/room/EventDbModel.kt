package es.usj.mastertsa.jveron.ticketsdb.data.repository.room

import androidx.room.Entity
import androidx.room.PrimaryKey

const val EVENTS_TABLE_NAME = "events"

@Entity(tableName = EVENTS_TABLE_NAME)
class EventDbModel (
    @PrimaryKey
    val event_id: Int,
    val price: Float,
    val name: String,
    val description: String)