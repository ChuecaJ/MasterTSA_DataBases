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
        // TODO
    }

}