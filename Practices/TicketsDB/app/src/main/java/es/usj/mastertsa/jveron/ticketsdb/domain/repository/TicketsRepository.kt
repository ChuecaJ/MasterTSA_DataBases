package es.usj.mastertsa.jveron.ticketsdb.domain.repository

import es.usj.mastertsa.jveron.ticketsdb.domain.model.Event
import es.usj.mastertsa.jveron.ticketsdb.domain.model.User
import es.usj.mastertsa.jveron.ticketsdb.domain.model.UserWithEvents
import kotlinx.coroutines.flow.Flow

interface TicketsRepository {

    suspend fun addEvent(event: Event)
    
    suspend fun getEvents(): Flow<List<Event>>

    suspend fun getUserWithEvents(userEmail: String): UserWithEvents

    suspend fun getUser(email: String): Flow<User>

    suspend fun addUser(user: User)
    
    suspend fun addUserAndEvent(user: User, event: Event)
}