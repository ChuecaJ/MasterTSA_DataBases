package es.usj.mastertsa.jchueca.cities.data.repository

import es.usj.mastertsa.jchueca.cities.data.repository.api.CityApiModel
import es.usj.mastertsa.jchueca.cities.data.repository.api.SightApiModel
import es.usj.mastertsa.jchueca.cities.data.repository.room.CityAndSightsDbModel
import es.usj.mastertsa.jchueca.cities.data.repository.room.CityDbModel
import es.usj.mastertsa.jchueca.cities.data.repository.room.SightDbModel
import es.usj.mastertsa.jchueca.cities.domain.model.City
import es.usj.mastertsa.jchueca.cities.domain.model.CityAndSights
import es.usj.mastertsa.jchueca.cities.domain.model.Sight

object CityMapper {

    fun mapCityFromApiToDomain(cityApiModel: CityApiModel): City{
        return City(
            id = cityApiModel.id,
            name = cityApiModel.name,
            description = cityApiModel.description,
            sunshineHours = cityApiModel.sunshineHours
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
    
    
    fun mapSightFromApiToDomain(sightApiModel: SightApiModel): Sight{
        return Sight(
            sight_id = sightApiModel.sight_id,
            city_id = sightApiModel.city_id,
            sight_name = sightApiModel.sight_name,
            sight_description = sightApiModel.sight_name
        )
    }
    
    
    fun mapSightFromDomainToDb (sight: Sight): SightDbModel {
        return SightDbModel(
            sight_id = sight.sight_id,
            city_id = sight.city_id,
            sight_name = sight.sight_name,
            sight_description = sight.sight_description
        )
    }
    
    fun mapSightFromDbToDomain (sightDbModel: SightDbModel): Sight {
        return Sight(
            sight_id = sightDbModel.sight_id,
            city_id = sightDbModel.city_id,
            sight_name = sightDbModel.sight_name,
            sight_description = sightDbModel.sight_description
        )
    }
    
    
    fun mapCityAndSightsFromDbToDomain (cityAndSightsDbModel: CityAndSightsDbModel): CityAndSights {
        return CityAndSights(
            mapCityFromDbToDomain(cityAndSightsDbModel.cityDbModel),
            cityAndSightsDbModel.sights.map { sightDbModel ->
                mapSightFromDbToDomain(sightDbModel)
            }
        )
    }

}