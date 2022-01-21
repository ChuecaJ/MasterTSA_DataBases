package es.usj.mastertsa.jchueca.cities.data.repository.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

const val TABLE_NAME_CITIES = "cities"
const val TABLE_NAME_SIGHTS = "sights"

@Dao
interface CityDao {
    
    @Insert
    suspend fun insertCity(cityDbModel: CityDbModel)
    
    @Query("SELECT * FROM $TABLE_NAME_CITIES")
    fun getCities(): Flow<List<CityDbModel>>
    
    
    @Insert(onConflict = REPLACE)
    suspend fun insertSight(sightDbModel: SightDbModel)
    
    @Query("SELECT * FROM $TABLE_NAME_CITIES WHERE id = :cityId")
    fun getCityAndSights(cityId: Int): Flow<CityAndSightsDbModel>
    
}