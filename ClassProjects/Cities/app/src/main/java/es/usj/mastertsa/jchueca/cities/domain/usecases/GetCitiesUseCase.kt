package es.usj.mastertsa.jchueca.cities.domain.usecases

import es.usj.mastertsa.jchueca.cities.domain.model.City
import es.usj.mastertsa.jchueca.cities.domain.model.CityFilter
import es.usj.mastertsa.jchueca.cities.domain.repository.CityRepository

const val HOURS_THRESHOLD = 1800

class GetCitiesUseCase(val repository: CityRepository) {
    fun getCities(): List<City>{
        val cities = repository.getCities()
        val filter = repository.getCityFilter()
        
        return when(filter){
            CityFilter.ALL_CITIES -> cities
            CityFilter.SUNNY_CITIES -> cities.filter { city -> city.sunshineHours >= HOURS_THRESHOLD }
            CityFilter.CLOUDY_CITIES -> cities.filter { city -> city.sunshineHours < HOURS_THRESHOLD }
            else -> cities
        }
    }
}