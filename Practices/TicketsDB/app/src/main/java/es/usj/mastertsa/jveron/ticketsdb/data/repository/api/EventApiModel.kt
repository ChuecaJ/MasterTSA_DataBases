package es.usj.mastertsa.jveron.ticketsdb.data.repository.api

import com.squareup.moshi.Json

class EventApiModel (
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "price") val price: Float,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "description") val description: String
)