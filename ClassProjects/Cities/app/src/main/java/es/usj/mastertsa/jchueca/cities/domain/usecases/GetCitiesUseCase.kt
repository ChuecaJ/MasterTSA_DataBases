package es.usj.mastertsa.jchueca.cities.domain.usecases

import es.usj.mastertsa.jchueca.cities.domain.model.City
import es.usj.mastertsa.jchueca.cities.domain.model.CityFilter
import es.usj.mastertsa.jchueca.cities.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

const val HOURS_THRESHOLD = 1800

class GetCitiesUseCase(val repository: CityRepository) {
    
    suspend fun getCities(): Flow<List<City>> {
        
        val filter = repository.getCityFilter().first()
        return repository.getCities().map { cityList ->
            when(filter){
                CityFilter.ALL_CITIES -> cityList
                CityFilter.SUNNY_CITIES -> cityList.filter { city -> city.sunshineHours >= HOURS_THRESHOLD }
                CityFilter.CLOUDY_CITIES -> cityList.filter { city -> city.sunshineHours < HOURS_THRESHOLD }
                else -> cityList
            }
        }
        
        
    }
    
}