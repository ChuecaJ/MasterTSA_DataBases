package es.usj.mastertsa.jveron.ticketsdb.data.repository.room

import androidx.room.Entity
import androidx.room.PrimaryKey

const val USERS_TABLE_NAME = "users"

@Entity(tableName = USERS_TABLE_NAME)//, indices = [Index(value = ["email"], unique = true)])
data class UserDbModel (
    @PrimaryKey
    val user_email: String,
    val password: String,
    val name: String)