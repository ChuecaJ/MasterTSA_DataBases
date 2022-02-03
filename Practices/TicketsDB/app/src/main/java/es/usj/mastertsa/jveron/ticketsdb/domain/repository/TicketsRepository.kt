package es.usj.mastertsa.jveron.ticketsdb.domain.repository

import es.usj.mastertsa.jveron.ticketsdb.domain.model.Event
import es.usj.mastertsa.jveron.ticketsdb.domain.model.User
import es.usj.mastertsa.jveron.ticketsdb.domain.model.UserWithEvents
import kotlinx.coroutines.flow.Flow

interface TicketsRepository {

    suspend fun addEvent(event: Event)
    
    suspend fun getEvents(): Flow<List<Event>>

    suspend fun getUserAndEvents(userId: Int): UserWithEvents

    suspend fun getUser(email: String): User

    suspend fun addUser(user: User)
}