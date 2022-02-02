package es.usj.mastertsa.jveron.ticketsdb.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.usj.mastertsa.jveron.ticketsdb.domain.usecases.UseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserAndEventsViewModel(
    private val userId: Int,
    private val useCases: UseCases
): ViewModel() {

    private val userAndEventsMutableStateFlow = MutableStateFlow<UserAndEventsState>(UserAndEventsState.Loading)
    val userAndEventsStateFlow: StateFlow<UserAndEventsState> = userAndEventsMutableStateFlow

    fun getData(){
        viewModelScope.launch {
            val userAndEvents = useCases.getUserAndEvents(userId)
            userAndEventsMutableStateFlow.emit(UserAndEventsState.Success(userAndEvents))
        }
    }

}