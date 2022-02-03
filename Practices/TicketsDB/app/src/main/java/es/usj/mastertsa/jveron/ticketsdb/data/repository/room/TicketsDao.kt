package es.usj.mastertsa.jveron.ticketsdb.data.repository.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TicketsDao {
    
    @Insert
    suspend fun insertUser(userDbModel: UserDbModel)
    
    @Query("SELECT * FROM $USERS_TABLE_NAME WHERE user_email = :email")
    fun getUser(email: String): Flow<UserDbModel>
    
    @Insert
    suspend fun insertEvent(sightDbModel: EventDbModel)
    
    @Query("SELECT * FROM $EVENTS_TABLE_NAME")
    fun getEvents(): Flow<List<EventDbModel>>
    
    @Query("SELECT * FROM $USERS_TABLE_NAME WHERE user_email = :email")
    suspend fun getUserAndEvents(email: String): UserWithEventsDbModel
    
    @Insert
    suspend fun insertUserAndEvent(crossRefDbModel: UserEventCrossRefDbModel)
    
}