package es.usj.mastertsa.jveron.ticketsdb.data

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import es.usj.mastertsa.jveron.ticketsdb.domain.model.User

val NO_USER = User("noEmail", "noPassword", "noName")

object SaveLoadPreferences {
    
    private const val FILE_NAME = "TicketsDB_App"

    public fun save (activity: AppCompatActivity, user: User) {
        
        val sp: SharedPreferences = activity.getSharedPreferences(
            FILE_NAME,
            Context.MODE_PRIVATE
        )
        
        val editor = sp.edit()
        editor.putString(
            "email",
            user.email
        )
        editor.putString(
            "password",
            user.password
        )
        editor.putString(
            "name",
            user.name
        )
        
        editor.apply()
    }
    
    public fun load (activity : AppCompatActivity) : User? {
        
        val sp: SharedPreferences = activity.getSharedPreferences(
            FILE_NAME,
            Context.MODE_PRIVATE
        )

        val email = sp.getString("email", NO_USER.email)!!
        val password = sp.getString("password", NO_USER.password)!!
        val name = sp.getString("name", NO_USER.name)!!

        if (email == NO_USER.email ||
            password == NO_USER.password ||
            name == NO_USER.name) {
            return null
        }

        return User(
            email = email,
            password = password,
            name = name
        )
        
    }
}