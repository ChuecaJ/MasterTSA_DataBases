package es.usj.mastertsa.jveron.ticketsdb.data.repository.api

import retrofit2.http.GET

interface EventsService {

    @GET("events")
    suspend fun getEvents() : List<EventApiModel>

}