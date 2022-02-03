package es.usj.mastertsa.jveron.ticketsdb.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.usj.mastertsa.jveron.ticketsdb.domain.model.User
import es.usj.mastertsa.jveron.ticketsdb.domain.usecases.UseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserAndEventsViewModel(
    private val user: User,
    private val useCases: UseCases
): ViewModel() {

    private val userAndEventsMutableStateFlow = MutableStateFlow<UserAndEventsState>(UserAndEventsState.Loading)
    val userAndEventsStateFlow: StateFlow<UserAndEventsState> = userAndEventsMutableStateFlow

    fun getData(){
        viewModelScope.launch {
            val userAndEvents = useCases.getUserAndEvents(user.email)
            userAndEventsMutableStateFlow.emit(UserAndEventsState.Success(userAndEvents))
        }
    }

}