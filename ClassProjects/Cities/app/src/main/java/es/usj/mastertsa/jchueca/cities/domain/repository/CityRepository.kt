package es.usj.mastertsa.jchueca.cities.domain.repository

import es.usj.mastertsa.jchueca.cities.domain.model.City
import es.usj.mastertsa.jchueca.cities.domain.model.CityFilter

interface CityRepository {

    fun getCities(): List<City>

    fun addCity(city: City)
    
    fun setFilter(cityFilter: CityFilter)
    
    fun getCityFilter (): CityFilter
}