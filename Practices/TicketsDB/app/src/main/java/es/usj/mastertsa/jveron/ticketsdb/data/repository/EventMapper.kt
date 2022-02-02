package es.usj.mastertsa.jveron.ticketsdb.data.repository

import es.usj.mastertsa.jveron.ticketsdb.data.repository.api.EventApiModel
import es.usj.mastertsa.jveron.ticketsdb.data.repository.room.EventDbModel
import es.usj.mastertsa.jveron.ticketsdb.domain.model.Event

object EventMapper {
    fun mapEventFromApiToDomain(eventApiModel: EventApiModel) : Event {
        return Event(
            id = eventApiModel.id,
            price = eventApiModel.price,
            name = eventApiModel.name,
            description = eventApiModel.description
        )
    }
    
    fun mapEventFromDbToDomain(eventDbModel: EventDbModel): Event {
        return Event(
            id = eventDbModel.event_id,
            price = eventDbModel.price,
            name = eventDbModel.name,
            description = eventDbModel.description
        )
    }
    
    fun mapEventFromDomainToDb (event: Event): EventDbModel {
        return EventDbModel(
            event_id = event.id,
            price = event.price,
            name = event.name,
            description = event.description
        )
    }
}