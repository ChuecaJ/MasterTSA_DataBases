package es.usj.mastertsa.jveron.ticketsdb.presentation.viewmodel

import es.usj.mastertsa.jveron.ticketsdb.domain.model.Event

sealed class EventState() {
    object Loading : EventState()
    data class Success(val data: List<Event>) : EventState()
    data class Failure(val throwable: Throwable) : EventState()
}