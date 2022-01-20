package es.usj.mastertsa.jchueca.cities.domain.usecases

import es.usj.mastertsa.jchueca.cities.domain.model.CityFilter
import es.usj.mastertsa.jchueca.cities.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow

class GetFilterUseCase(private val repository: CityRepository) {

    suspend fun getFilter(): Flow<CityFilter> {
        return repository.getCityFilter()
    }
}