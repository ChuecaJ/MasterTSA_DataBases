package es.usj.mastertsa.jchueca.cities.data.repository.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TABLE_NAME_CITIES)
data class CityDbModel (
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val sunshineHours: Int
)