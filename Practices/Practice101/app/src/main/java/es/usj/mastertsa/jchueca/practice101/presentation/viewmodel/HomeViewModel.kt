package es.usj.mastertsa.jchueca.practice101.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.usj.mastertsa.jchueca.practice101.domain.model.PracticeData
import es.usj.mastertsa.jchueca.practice101.domain.usecases.AddPracticeData
import es.usj.mastertsa.jchueca.practice101.domain.usecases.DeletePracticeData
import es.usj.mastertsa.jchueca.practice101.domain.usecases.GetPracticeData
import es.usj.mastertsa.jchueca.practice101.domain.usecases.UpdatePracticeData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getPracticeData: GetPracticeData,
    private val addPracticeData: AddPracticeData,
    private val updatePracticeData: UpdatePracticeData,
    private val deletePracticeData: DeletePracticeData
) : ViewModel() {
    
    private val practiceMutableStateFlow: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.Loading)
    val practiceStateFlow: StateFlow<HomeState> = practiceMutableStateFlow
    
    fun getData() {
        viewModelScope.launch {
            practiceMutableStateFlow.emit(HomeState.Loading)
            getPracticeData.getPracticeData().collect { practiceData ->
                practiceMutableStateFlow.emit(HomeState.Success(practiceData))
            }
        }
    }
    
    fun addData(practiceData: PracticeData) {
        viewModelScope.launch {
            addPracticeData.addPracticeData(practiceData)
        }
    }
    
    fun updateData(practiceData: PracticeData) {
        viewModelScope.launch {
            updatePracticeData.updatePracticeData(practiceData)
        }
    }
    
    fun deleteData() {
        viewModelScope.launch {
            deletePracticeData.deletePracticeData()
        }
    }
}

