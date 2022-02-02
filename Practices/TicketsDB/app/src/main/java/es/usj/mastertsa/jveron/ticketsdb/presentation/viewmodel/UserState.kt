package es.usj.mastertsa.jveron.ticketsdb.presentation.viewmodel

import es.usj.mastertsa.jveron.ticketsdb.domain.model.User

sealed class UserState {
    object Loading : UserState()
    data class Success(val data: User) : UserState()
    data class Failure(val throwable: Throwable) : UserState()
}