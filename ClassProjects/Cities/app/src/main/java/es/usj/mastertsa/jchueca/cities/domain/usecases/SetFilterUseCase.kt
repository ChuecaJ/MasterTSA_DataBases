package es.usj.mastertsa.jchueca.cities.domain.usecases

import es.usj.mastertsa.jchueca.cities.domain.model.CityFilter
import es.usj.mastertsa.jchueca.cities.domain.repository.CityRepository

class SetFilterUseCase (private val cityRepository: CityRepository) {
    
    suspend fun setFilter(cityFilter: CityFilter){
        cityRepository.setCityFilter(cityFilter)
    }
}