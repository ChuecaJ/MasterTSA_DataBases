package es.usj.mastertsa.jchueca.cities.presentation.viewmodel

import es.usj.mastertsa.jchueca.cities.domain.model.CityAndSights

sealed class CityDetailState {
    object Loading: CityDetailState()
    data class Success(val data: CityAndSights): CityDetailState()
    data class Failure(val throwable: Throwable): CityDetailState()
}