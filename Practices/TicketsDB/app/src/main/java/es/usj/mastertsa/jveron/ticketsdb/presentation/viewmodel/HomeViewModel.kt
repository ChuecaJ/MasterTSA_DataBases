package es.usj.mastertsa.jveron.ticketsdb.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.usj.mastertsa.jveron.ticketsdb.domain.model.Event
import es.usj.mastertsa.jveron.ticketsdb.domain.model.User
import es.usj.mastertsa.jveron.ticketsdb.domain.usecases.UseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class HomeViewModel(val useCases: UseCases): ViewModel() {

    private val eventsMutableStateFlow = MutableStateFlow<EventState>(EventState.Loading)
    val eventsStateFlow: StateFlow<EventState> = eventsMutableStateFlow

    private val userMutableStateFlow = MutableStateFlow<UserState>(UserState.Loading)
    val usersStateFlow: StateFlow<UserState> = userMutableStateFlow

    private val userAndEventsMutableStateFlow = MutableStateFlow<UserAndEventsState>(UserAndEventsState.Loading)
    val userAndEventsStateFlow: StateFlow<UserAndEventsState> = userAndEventsMutableStateFlow

    private val triggerFlow = MutableStateFlow(false)

    fun getData() {
        viewModelScope.launch {
            triggerFlow.flatMapLatest {
                useCases.getEvents()
            }
                .collect { eventsList ->
                    eventsMutableStateFlow.emit(EventState.Success(eventsList))
                }
        }
    }

    fun addUserAndEvent(user: User, event: Event) {
        viewModelScope.launch {
            useCases.addUserAndEvent(user = user, event = event)
        }
    }

    fun getUser(email: String) {
        viewModelScope.launch {
            userMutableStateFlow.emit(UserState.Loading)

            triggerFlow.flatMapLatest {
                useCases.getUser(email)
            }
                .collect { eventsList ->
                    userMutableStateFlow.emit(UserState.Success(eventsList))
                }
        }
    }

    fun addUser(user: User) {
        viewModelScope.launch {
            useCases.addUser(user = user)
        }
    }

    fun getUserAndEvents(user: User) {
        viewModelScope.launch {
            userAndEventsMutableStateFlow.emit(UserAndEventsState.Loading)
            val userAndEvents = useCases.getUserAndEvents(user.email)
            userAndEventsMutableStateFlow.emit(UserAndEventsState.Success(userAndEvents))
        }
    }

}