package es.usj.mastertsa.jchueca.cities.presentation.viewmodel

import es.usj.mastertsa.jchueca.cities.domain.model.City

sealed class CityState(){
    object Loading: CityState()
    data class Success(val data: List<City>): CityState()
    data class Failure(val throwable: Throwable): CityState()
}
