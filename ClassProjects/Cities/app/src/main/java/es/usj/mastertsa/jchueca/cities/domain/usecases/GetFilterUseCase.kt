package es.usj.mastertsa.jchueca.cities.domain.usecases

import es.usj.mastertsa.jchueca.cities.domain.model.CityFilter
import es.usj.mastertsa.jchueca.cities.domain.repository.CityRepository

class GetFilterUseCase(private val repository: CityRepository) {

    fun getFilter(): CityFilter{
        return repository.getCityFilter()
    }
}