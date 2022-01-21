package es.usj.mastertsa.jchueca.cities.data.repository.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TABLE_NAME_SIGHTS)
data class SightDbModel (
    @PrimaryKey
    val sight_id: Int,
    val city_id: Int,
    val sight_name: String,
    val sight_description: String
)
