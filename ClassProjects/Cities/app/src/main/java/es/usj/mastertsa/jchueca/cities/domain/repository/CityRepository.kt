package es.usj.mastertsa.jchueca.cities.domain.repository

import es.usj.mastertsa.jchueca.cities.domain.model.City
import es.usj.mastertsa.jchueca.cities.domain.model.CityAndSights
import es.usj.mastertsa.jchueca.cities.domain.model.CityFilter
import es.usj.mastertsa.jchueca.cities.domain.model.Sight
import kotlinx.coroutines.flow.Flow

interface CityRepository {

    suspend fun getCities(): Flow<List<City>>

    suspend fun addCity(city: City)
    
    suspend fun setCityFilter(cityFilter: CityFilter)
    
    suspend fun getCityFilter (): Flow<CityFilter>
    
    suspend fun addSight(sight: Sight)
    
    suspend fun getCityAndSights(cityId: Int): Flow<CityAndSights>
}