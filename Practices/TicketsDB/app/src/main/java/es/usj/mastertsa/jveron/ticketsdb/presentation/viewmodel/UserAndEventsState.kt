package es.usj.mastertsa.jveron.ticketsdb.presentation.viewmodel

import es.usj.mastertsa.jveron.ticketsdb.domain.model.UserAndEvents

sealed class UserAndEventsState {
    object Loading : UserAndEventsState()
    data class Success(val data: UserAndEvents) : UserAndEventsState()
    data class Failure(val throwable: Throwable) : UserAndEventsState()
}