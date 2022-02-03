package es.usj.mastertsa.jveron.ticketsdb.data.repository.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserDbModel::class, EventDbModel::class, UserEventCrossRefDbModel::class], version = 1, exportSchema = true)
abstract class TicketsDatabase: RoomDatabase() {
    
    abstract fun getDao(): TicketsDao
    
    companion object {
    
        @Volatile
        private var INSTANCE : TicketsDatabase? = null
    
        fun getDatabase(context: Context): TicketsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    TicketsDatabase::class.java,
                    "TicketsRoom.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}