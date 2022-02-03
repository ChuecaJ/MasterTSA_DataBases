package es.usj.mastertsa.jveron.ticketsdb.data.repository.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TicketsDao {
    
    @Insert
    suspend fun insertUser(userDbModel: UserDbModel)
    
    @Query("SELECT * FROM $USERS_TABLE_NAME WHERE email = :email")
    fun getUser(email: String): Flow<UserDbModel>
    
    @Insert
    suspend fun insertEvent(sightDbModel: EventDbModel)
    
    @Query("SELECT * FROM $EVENTS_TABLE_NAME")
    suspend fun getEvents(): Flow<List<EventDbModel>>
    
    @Query("SELECT * FROM $USERS_TABLE_NAME WHERE user_id = :userId")
    suspend fun getUserAndEvents(userId: Int): UserWithEventsDbModel
    
    @Insert
    suspend fun insertUserAndEvent(crossRefDbModel: UserEventCrossRefDbModel)
    
}