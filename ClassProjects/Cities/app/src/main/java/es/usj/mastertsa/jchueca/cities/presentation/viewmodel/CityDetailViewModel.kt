package es.usj.mastertsa.jchueca.cities.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.usj.mastertsa.jchueca.cities.domain.usecases.GetCityAndSightsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CityDetailViewModel (
    val getCityAndSightsUseCase: GetCityAndSightsUseCase
): ViewModel()  {
    
    private val cityDetailMutableStateFlow = MutableStateFlow<CityDetailState>(CityDetailState.Loading)
    val cityDetailStateFlow: StateFlow<CityDetailState> = cityDetailMutableStateFlow
    
    
    fun getCityAndSights (cityId: Int){
        viewModelScope.launch {
            getCityAndSightsUseCase.getCityAndSights(cityId).collect { cityAndSights ->
                cityDetailMutableStateFlow.emit(CityDetailState.Success(cityAndSights))
            }
        }
    }
}