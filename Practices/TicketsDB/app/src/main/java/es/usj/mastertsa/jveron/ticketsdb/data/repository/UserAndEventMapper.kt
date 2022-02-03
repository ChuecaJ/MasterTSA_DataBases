package es.usj.mastertsa.jveron.ticketsdb.data.repository

import es.usj.mastertsa.jveron.ticketsdb.data.repository.room.UserEventCrossRefDbModel
import es.usj.mastertsa.jveron.ticketsdb.domain.model.Event
import es.usj.mastertsa.jveron.ticketsdb.domain.model.User
import es.usj.mastertsa.jveron.ticketsdb.domain.model.UserAndEvent


object UserAndEventMapper {
    
    fun mapFromDomainToDb (user: User, event: Event): UserEventCrossRefDbModel {
        return UserEventCrossRefDbModel(
            user_email = user.email,
            event_id = event.id
        )
    }
}