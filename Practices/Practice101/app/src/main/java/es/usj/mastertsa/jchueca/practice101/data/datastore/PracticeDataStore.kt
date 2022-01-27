package es.usj.mastertsa.jchueca.practice101.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import es.usj.mastertsa.jchueca.practice101.data.datastore.PreferencesKeys.PRACTICE_NAME_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val PRACTICE_DATA_STORE = "practice_data_store"

private val Context.practiceDataStore by preferencesDataStore(
    name = PRACTICE_DATA_STORE
)

private object PreferencesKeys {
    val PRACTICE_NAME_KEY = stringPreferencesKey("practice_name_string")
}

class PracticeDataStore(private val context: Context) {
    
    fun getDataStoreData(): Flow<String> {
        return context.practiceDataStore.data.map { preferences ->
            val practiceName = preferences[PRACTICE_NAME_KEY] ?: ""
            practiceName
        }
    }
    
    suspend fun addDataStoreData(value: String) {
        context.practiceDataStore.edit { preferences ->
            preferences[PRACTICE_NAME_KEY] = value
        }
    }
    
    suspend fun deleteDataStoreData() {
        context.practiceDataStore.edit { preferences ->
            preferences.clear()
        }
    }
    
    suspend fun updateDataStoreData(value: String) {
        context.practiceDataStore.edit { preferences ->
            preferences[PRACTICE_NAME_KEY] = value
        }
    }
}