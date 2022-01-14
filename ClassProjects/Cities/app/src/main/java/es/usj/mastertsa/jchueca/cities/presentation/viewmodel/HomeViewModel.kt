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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
    
    fun setFilter (cityFilter: CityFilter){
        setFilterUseCase.setFilter(cityFilter)
        getData()
        getFilter()
    }
    
    fun getFilter () {
        viewModelScope.launch {
            val filter = getFilterUseCase.getFilter()
            filterCityMutableStateFlow.emit(filter)
        }
    }

}