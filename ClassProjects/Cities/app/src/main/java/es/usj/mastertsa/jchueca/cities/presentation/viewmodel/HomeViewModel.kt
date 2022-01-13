package es.usj.mastertsa.jchueca.cities.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.usj.mastertsa.jchueca.cities.domain.model.City
import es.usj.mastertsa.jchueca.cities.domain.usecases.AddCityUseCase
import es.usj.mastertsa.jchueca.cities.domain.usecases.GetCitiesUseCase
import es.usj.mastertsa.jchueca.cities.presentation.viewmodel.CityState.Loading
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    val addCityUseCase: AddCityUseCase,
    val getCitiesUseCase: GetCitiesUseCase
): ViewModel() {

    private val citiesMutableStateFlow = MutableStateFlow<CityState>(Loading)
    val citiesStateFlow: StateFlow<CityState> = citiesMutableStateFlow


    fun getData(){
        viewModelScope.launch {
            delay(1000)
            val newCities = getCitiesUseCase.getCities()
            citiesMutableStateFlow.emit(CityState.Success(newCities))
        }
    }

    fun addCity (city: City) {
        addCityUseCase.addCity(city)
        getData()
    }

}