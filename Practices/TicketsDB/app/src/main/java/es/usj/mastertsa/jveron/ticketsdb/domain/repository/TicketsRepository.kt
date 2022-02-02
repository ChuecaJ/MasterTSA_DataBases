package es.usj.mastertsa.jveron.ticketsdb.domain.repository

import es.usj.mastertsa.jveron.ticketsdb.domain.model.Event
import es.usj.mastertsa.jveron.ticketsdb.domain.model.User
import es.usj.mastertsa.jveron.ticketsdb.domain.model.UserAndEvents
import kotlinx.coroutines.flow.Flow

interface TicketsRepository {

    suspend fun getEvents(): Flow<List<Event>>

    suspend fun getUserAndEvents(userId: Int): UserAndEvents

    suspend fun getUser(email: String): User

}