package es.usj.mastertsa.jchueca.cities.data.repository

import es.usj.mastertsa.jchueca.cities.domain.model.City
import es.usj.mastertsa.jchueca.cities.domain.repository.CityRepository
import es.usj.mastertsa.jchueca.cities.presentation.viewmodel.CityState

class CityRepositoryImpl: CityRepository {

    private val cities = getFakeData()

    override fun getCities(): List<City> {

        val newCities = mutableListOf<City>()
        newCities.addAll(cities)
        return newCities
    }

    override fun addCity(city: City) {
        cities.add(city)
    }



    fun getFakeData(): MutableList<City> {

        val cities = mutableListOf<City>()

        cities.add(City(id = 1, name = "Zaragoza", description = "Guay!1"))
        cities.add(City(id = 2, name = "Madrid", description = "Guay!2"))
        cities.add(City(id = 3, name = "Logroño", description = "Guay!3"))

        return cities
    }
}