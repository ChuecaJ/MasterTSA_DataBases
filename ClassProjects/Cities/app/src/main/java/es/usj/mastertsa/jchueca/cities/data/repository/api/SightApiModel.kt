package es.usj.mastertsa.jchueca.cities.data.repository.api

import com.squareup.moshi.Json

class SightApiModel (
    @field:Json(name="sight_id") val sight_id: Int,
    @field:Json(name="city_id") val city_id: Int,
    @field:Json(name="sight_name") val sight_name: String,
    @field:Json(name="sight_description") val sight_description: String
    ) {
}