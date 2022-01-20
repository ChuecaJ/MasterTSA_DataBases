package es.usj.mastertsa.jchueca.cities.data.repository.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import es.usj.mastertsa.jchueca.cities.data.repository.slite.CityContract.CityEntity.TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {
    
    @Insert
    suspend fun insertCity(cityDbModel: CityDbModel)
    
    @Query("SELECT * FROM $TABLE_NAME")
    fun getCities(): Flow<List<CityDbModel>>

}