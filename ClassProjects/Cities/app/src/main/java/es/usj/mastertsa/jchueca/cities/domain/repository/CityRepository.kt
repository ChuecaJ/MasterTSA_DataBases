package es.usj.mastertsa.jchueca.cities.domain.repository

import es.usj.mastertsa.jchueca.cities.domain.model.City

interface CityRepository {

    fun getCities(): List<City>

    fun addCity(city: City)
}