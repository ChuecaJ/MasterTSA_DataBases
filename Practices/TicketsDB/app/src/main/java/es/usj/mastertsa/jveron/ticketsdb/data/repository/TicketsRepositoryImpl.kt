package es.usj.mastertsa.jveron.ticketsdb.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import es.usj.mastertsa.jveron.ticketsdb.data.NO_USER
import es.usj.mastertsa.jveron.ticketsdb.data.repository.EventKey.timestamp
import es.usj.mastertsa.jveron.ticketsdb.data.repository.api.EventsService
import es.usj.mastertsa.jveron.ticketsdb.data.repository.room.TicketsDao
import es.usj.mastertsa.jveron.ticketsdb.data.repository.room.UserDbModel
import es.usj.mastertsa.jveron.ticketsdb.domain.model.Event
import es.usj.mastertsa.jveron.ticketsdb.domain.model.User
import es.usj.mastertsa.jveron.ticketsdb.domain.model.UserWithEvents
import es.usj.mastertsa.jveron.ticketsdb.domain.repository.TicketsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


const val TIMESTAMP = "timeStamp"

const val FIVE_DAYS = 432000000

object EventKey{
    val timestamp = longPreferencesKey(TIMESTAMP)
}

const val TICKETS_DATA_STORE = "TICKETS_DATA_STORE"

val Context.dataStore by preferencesDataStore(
    name = TICKETS_DATA_STORE
)

class TicketsRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
    private val eventsService: EventsService,
    private val TicketsDao: TicketsDao
): TicketsRepository {
    
    override suspend fun addEvent(event: Event) {
        val eventToAdd = EventMapper.mapEventFromDomainToDb(event)
        TicketsDao.insertEvent(eventToAdd)
    }
    
    override suspend fun getEvents(): Flow<List<Event>> {
        
        if(shouldRefresh()){
            eventsService.getEvents().forEach { eventApiModel ->
                val event = EventMapper.mapEventFromApiToDomain(eventApiModel)
                addEvent(event)
            }
        
            dataStore.edit { mutablePreferences ->
                mutablePreferences[timestamp] = System.currentTimeMillis()
            }
        }
    
        return TicketsDao.getEvents().map { eventList ->
            eventList.map { eventDb ->
                EventMapper.mapEventFromDbToDomain(eventDb)
            }
        }
    }

    override suspend fun getUserWithEvents(userEmail: String): UserWithEvents {
    
        val userAndEvents = TicketsDao.getUserAndEvents(userEmail)
        val user = UserMapper.mapUserFromDbToDomain(userAndEvents.user)
        val events = userAndEvents.events.map { eventDb ->
            EventMapper.mapEventFromDbToDomain(eventDb)
        }
        return UserWithEvents(user, events)
    }
    
    override suspend fun getUser(email: String): Flow<User> {
        
        val userDb = TicketsDao.getUser(email)
        
        return userDb.map { userDbModel ->
            if (userDbModel == null) {
                NO_USER
            }
            else {
                UserMapper.mapUserFromDbToDomain(userDbModel)
            }
        }
    }
    
    override suspend fun addUser(user: User) {
        val userToAdd = UserMapper.mapUserFromDomainToDb(user)
        TicketsDao.insertUser(userToAdd)
    }
    
    override suspend fun addUserAndEvent(user: User, event: Event) {
        val crossRefDbModel = UserAndEventMapper.mapFromDomainToDb(user, event)
        TicketsDao.insertUserAndEvent(crossRefDbModel)
    }
    
    private suspend fun shouldRefresh(): Boolean{
        val timestamp = dataStore.data.map { preference ->
            preference[timestamp] ?: 0
        }.first()
        
        return System.currentTimeMillis() - timestamp > FIVE_DAYS
    }
}