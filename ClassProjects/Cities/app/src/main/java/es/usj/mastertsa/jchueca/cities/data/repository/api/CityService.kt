package es.usj.mastertsa.jchueca.cities.data.repository.api

import retrofit2.http.GET

interface CityService {
    
    @GET("/cities")
    suspend fun getCities(): List<CityApiModel>
}