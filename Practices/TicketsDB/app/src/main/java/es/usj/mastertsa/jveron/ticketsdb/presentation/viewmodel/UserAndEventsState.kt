package es.usj.mastertsa.jveron.ticketsdb.presentation.viewmodel

import es.usj.mastertsa.jveron.ticketsdb.domain.model.UserWithEvents

sealed class UserAndEventsState {
    object Loading : UserAndEventsState()
    data class Success(val data: UserWithEvents) : UserAndEventsState()
    data class Failure(val throwable: Throwable) : UserAndEventsState()
}