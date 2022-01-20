package es.usj.mastertsa.jchueca.cities.data.repository.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import es.usj.mastertsa.jchueca.cities.data.repository.slite.CityContract.CityEntity.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class CityDbModel (
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val sunshineHours: Int
)