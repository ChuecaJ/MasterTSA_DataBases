package es.usj.mastertsa.jveron.ticketsdb.data

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import es.usj.mastertsa.jveron.ticketsdb.domain.model.User

object SaveLoadPreferences {
    
    private const val FILE_NAME = "TicketsDB_App"
    
    public fun save (activity: AppCompatActivity, user: User) {
        
        val sp: SharedPreferences = activity.getSharedPreferences(
            FILE_NAME,
            Context.MODE_PRIVATE
        )
        
        val editor = sp.edit()
        editor.putString(
            "id",
            user.id.toString()
        )
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
    
    public fun load (activity : AppCompatActivity) : User {
        
        val sp: SharedPreferences = activity.getSharedPreferences(
            FILE_NAME,
            Context.MODE_PRIVATE
        )
        
        val id = sp.getString("id", "-1")!!.toInt()
        val email = sp.getString("email", "noEmail")!!
        val password = sp.getString("password", "noPassword")!!
        val name = sp.getString("name", "noName")!!
        
        return User(
            id = id,
            email = email,
            password = password,
            name = name
        )
        
    }
}