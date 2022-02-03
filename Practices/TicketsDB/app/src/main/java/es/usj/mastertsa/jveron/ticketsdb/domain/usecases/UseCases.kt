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

    suspend fun getUserAndEvents(userEmail: String): UserWithEvents {
        return ticketsRepository.getUserWithEvents(userEmail)
    }

    suspend fun getUser(email: String): Flow<User> {
        return ticketsRepository.getUser(email)
    }
    
    suspend fun addUser(user: User) {
        ticketsRepository.addUser(user)
    }
    
    suspend fun addUserAndEvent(user: User, event: Event){
        ticketsRepository.addUserAndEvent(user, event)
    }

}