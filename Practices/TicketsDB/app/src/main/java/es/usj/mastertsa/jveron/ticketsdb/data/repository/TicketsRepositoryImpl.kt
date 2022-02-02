package es.usj.mastertsa.jveron.ticketsdb.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import es.usj.mastertsa.jveron.ticketsdb.data.repository.api.EventsService
import es.usj.mastertsa.jveron.ticketsdb.data.repository.room.TicketsDao
import es.usj.mastertsa.jveron.ticketsdb.domain.model.Event
import es.usj.mastertsa.jveron.ticketsdb.domain.model.User
import es.usj.mastertsa.jveron.ticketsdb.domain.model.UserAndEvents
import es.usj.mastertsa.jveron.ticketsdb.domain.repository.TicketsRepository
import kotlinx.coroutines.flow.Flow

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