package es.usj.mastertsa.jchueca.cities.data.repository.room

import androidx.room.Embedded
import androidx.room.Relation

data class CityAndSightsDbModel (
    @Embedded val cityDbModel: CityDbModel,
    @Relation(
        parentColumn = "id",
        entityColumn = "city_id"
    )
    val sights: List<SightDbModel>
)