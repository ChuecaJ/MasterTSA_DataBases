package es.usj.mastertsa.jchueca.cities.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import es.usj.mastertsa.jchueca.cities.data.repository.CityFilterKey.cityFilterKey
import es.usj.mastertsa.jchueca.cities.data.repository.CityFilterKey.timestamp
import es.usj.mastertsa.jchueca.cities.data.repository.api.CityService
import es.usj.mastertsa.jchueca.cities.data.repository.room.CityDao
import es.usj.mastertsa.jchueca.cities.domain.model.City
import es.usj.mastertsa.jchueca.cities.domain.model.CityAndSights
import es.usj.mastertsa.jchueca.cities.domain.model.CityFilter
import es.usj.mastertsa.jchueca.cities.domain.model.Sight
import es.usj.mastertsa.jchueca.cities.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

const val CITIES_PREFERENCE = "CitiesPreference"
const val CITY_FILTER_KEY = "CityFilterKey"
const val TIMESTAMP = "timestamp"
const val FIVE_DAYS = 432000000

const val CITY_DATA_STORE = "CITY_DATA_STORE"

val Context.dataStore by preferencesDataStore(
    name = CITY_DATA_STORE
)

object CityFilterKey{
    val cityFilterKey = stringPreferencesKey(CITY_FILTER_KEY)
    val timestamp = longPreferencesKey(TIMESTAMP)
}

class CityRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
    private val cityService: CityService,
    private val cityDao: CityDao
    ): CityRepository {


    override suspend fun getCities(): Flow<List<City>> {
        
        if(shouldRefresh()){
            cityService.getCities().forEach { cityApiModel ->
                val city = CityMapper.mapCityFromApiToDomain(cityApiModel)
                addCity(city)
                cityApiModel.sights.forEach { sightApiModel ->
                    val sight = CityMapper.mapSightFromApiToDomain(sightApiModel)
                    addSight(sight)
                }
            }
    
            dataStore.edit { mutablePreferences ->
                mutablePreferences[timestamp] = System.currentTimeMillis()
            }
        }
        
        return cityDao.getCities().map { cityList ->
            cityList.map { city ->
                CityMapper.mapCityFromDbToDomain(city)
            }
        
        }
    }

    override suspend fun addCity(city: City) {
        val cityToAdd = CityMapper.mapCityFromDomainToDb(city)
        cityDao.insertCity(cityToAdd)
        
    }
    
    override suspend fun setCityFilter(cityFilter: CityFilter) {
        
        dataStore.edit { preferences ->
            preferences[cityFilterKey] = cityFilter.name
        }
        
    }
    
    override suspend fun getCityFilter(): Flow<CityFilter> {
        return dataStore.data.map { preferences ->
            val filter = preferences[cityFilterKey] ?: CityFilter.ALL_CITIES.name
            CityFilter.valueOf(filter)
        }
    }
    
    override suspend fun addSight(sight: Sight) {
        val sightToAdd = CityMapper.mapSightFromDomainToDb(sight)
        cityDao.insertSight(sightToAdd)
    }
    
    override suspend fun getCityAndSights(cityId: Int): Flow<CityAndSights> {
        return cityDao.getCityAndSights(cityId).map { cityAndSights ->
            CityMapper.mapCityAndSightsFromDbToDomain(cityAndSights)
        }
    
        
    }
    
    private suspend fun shouldRefresh(): Boolean{
        val timestamp = dataStore.data.map { preference ->
            preference[timestamp] ?: 0
        }.first()
        
        return System.currentTimeMillis() - timestamp > FIVE_DAYS
    }
    
}