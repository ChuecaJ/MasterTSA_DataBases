package es.usj.mastertsa.jchueca.cities.domain.usecases

import es.usj.mastertsa.jchueca.cities.domain.model.City
import es.usj.mastertsa.jchueca.cities.domain.repository.CityRepository

class GetCitiesUseCase(val repository: CityRepository) {
    fun getCities(): List<City>{
        return repository.getCities()
    }
}