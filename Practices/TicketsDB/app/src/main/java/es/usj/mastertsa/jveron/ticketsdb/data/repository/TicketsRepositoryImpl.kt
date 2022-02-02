package es.usj.mastertsa.jveron.ticketsdb.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import es.usj.mastertsa.jveron.ticketsdb.data.repository.api.EventsService
import es.usj.mastertsa.jveron.ticketsdb.data.repository.room.TicketsDao
import es.usj.mastertsa.jveron.ticketsdb.domain.model.Event
import es.usj.mastertsa.jveron.ticketsdb.domain.model.User
import es.usj.mastertsa.jveron.ticketsdb.domain.model.UserAndEvents
import es.usj.mastertsa.jveron.ticketsdb.domain.repository.TicketsRepository
import kotlinx.coroutines.flow.Flow

const val TICKETS_DATA_STORE = "TICKETS_DATA_STORE"

val Context.dataStore by preferencesDataStore(
    name = TICKETS_DATA_STORE
)

class TicketsRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
    private val eventsService: EventsService,
    private val TicketsDao: TicketsDao
): TicketsRepository {
    override suspend fun getEvents(): Flow<List<Event>> {
        TODO("Not yet implemented")
    }
    
    override suspend fun getUserAndEvents(userId: Int): UserAndEvents {
        TODO("Not yet implemented")
    }
    
    override suspend fun getUser(email: String): User {
        TODO("Not yet implemented")
    }
}