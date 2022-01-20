package es.usj.mastertsa.jchueca.cities.data.repository

import es.usj.mastertsa.jchueca.cities.data.repository.api.CityApiModel
import es.usj.mastertsa.jchueca.cities.data.repository.room.CityDbModel
import es.usj.mastertsa.jchueca.cities.domain.model.City

object CityMapper {

    fun mapCityFromApiToDomain(cityApiModel: CityApiModel): City{
        return City(
            cityApiModel.id,
            cityApiModel.name,
            cityApiModel.description,
            cityApiModel.sunshineHours
        )
    }
    
    fun mapCityFromDbToDomain (cityDbModel: CityDbModel): City {
        return City(
            id = cityDbModel.id,
            name = cityDbModel.name,
            description = cityDbModel.description,
            sunshineHours = cityDbModel.sunshineHours
        )
    }
    
    fun mapCityFromDomainToDb (city: City): CityDbModel {
        return CityDbModel(
            id = city.id,
            name = city.name,
            description = city.description,
            sunshineHours = city.sunshineHours
        )
    }

}