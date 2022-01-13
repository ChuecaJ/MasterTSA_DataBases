package es.usj.mastertsa.jchueca.cities.domain.usecases

import es.usj.mastertsa.jchueca.cities.domain.model.City
import es.usj.mastertsa.jchueca.cities.domain.repository.CityRepository

class AddCityUseCase (val repository: CityRepository) {

    fun addCity(city: City){
        repository.addCity(city)
    }
}