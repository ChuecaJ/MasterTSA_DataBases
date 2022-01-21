package es.usj.mastertsa.jchueca.cities.domain.usecases

import es.usj.mastertsa.jchueca.cities.domain.model.CityAndSights
import es.usj.mastertsa.jchueca.cities.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow

class GetCityAndSightsUseCase(private val repository: CityRepository) {
    
    suspend fun getCityAndSights(cityId: Int): Flow<CityAndSights> {
        return repository.getCityAndSights(cityId)
    }
}