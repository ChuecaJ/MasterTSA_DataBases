package es.usj.mastertsa.jveron.ticketsdb.domain.usecases

import es.usj.mastertsa.jveron.ticketsdb.domain.model.Event
import es.usj.mastertsa.jveron.ticketsdb.domain.model.User
import es.usj.mastertsa.jveron.ticketsdb.domain.model.UserWithEvents
import es.usj.mastertsa.jveron.ticketsdb.domain.repository.TicketsRepository
import kotlinx.coroutines.flow.Flow

class UseCases (private val ticketsRepository: TicketsRepository){

    suspend fun getEvents(): Flow<List<Event>> {
        return ticketsRepository.getEvents()
    }

    suspend fun getUserAndEvents(userId: Int): UserWithEvents {
        return ticketsRepository.getUserAndEvents(userId)
    }

    suspend fun getUser(email: String): User {
        return ticketsRepository.getUser(email)
    }

}