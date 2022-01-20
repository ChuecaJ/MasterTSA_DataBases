package es.usj.mastertsa.jchueca.cities.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.usj.mastertsa.jchueca.cities.domain.model.City
import es.usj.mastertsa.jchueca.cities.domain.model.CityFilter
import es.usj.mastertsa.jchueca.cities.domain.usecases.AddCityUseCase
import es.usj.mastertsa.jchueca.cities.domain.usecases.GetCitiesUseCase
import es.usj.mastertsa.jchueca.cities.domain.usecases.GetFilterUseCase
import es.usj.mastertsa.jchueca.cities.domain.usecases.SetFilterUseCase
import es.usj.mastertsa.jchueca.cities.presentation.viewmodel.CityState.Loading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class HomeViewModel(
    val addCityUseCase: AddCityUseCase,
    val getCitiesUseCase: GetCitiesUseCase,
    val setFilterUseCase: SetFilterUseCase,
    val getFilterUseCase: GetFilterUseCase
): ViewModel() {

    private val citiesMutableStateFlow = MutableStateFlow<CityState>(Loading)
    val citiesStateFlow: StateFlow<CityState> = citiesMutableStateFlow

    private val filterCityMutableStateFlow = MutableStateFlow(CityFilter.ALL_CITIES)
    val filterCityState: StateFlow<CityFilter> = filterCityMutableStateFlow

    private val triggerFlow = MutableStateFlow(false)
    
    fun getData(){
        viewModelScope.launch {
            triggerFlow.flatMapLatest {
                getCitiesUseCase.getCities()
            }.collect { cityList ->
                citiesMutableStateFlow.emit(CityState.Success(cityList))
            }
        }
    }

    fun addCity (city: City) {
        viewModelScope.launch {
            addCityUseCase.addCity(city)
        }
    }
    
    fun setFilter (cityFilter: CityFilter){
        viewModelScope.launch {
            setFilterUseCase.setFilter(cityFilter)
            triggerFlow.value = !triggerFlow.value
        }
    }
    
    fun getFilter () {
        viewModelScope.launch {
            getFilterUseCase.getFilter().collect { filter ->
                filterCityMutableStateFlow.emit(filter)
            }
        }
    }

}