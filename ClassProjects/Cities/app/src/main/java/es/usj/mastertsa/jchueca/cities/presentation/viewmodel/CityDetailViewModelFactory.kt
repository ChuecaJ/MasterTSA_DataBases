package es.usj.mastertsa.jchueca.cities.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.usj.mastertsa.jchueca.cities.data.repository.CityRepositoryImpl
import es.usj.mastertsa.jchueca.cities.data.repository.api.CityService
import es.usj.mastertsa.jchueca.cities.data.repository.dataStore
import es.usj.mastertsa.jchueca.cities.data.repository.room.CityDatabase
import es.usj.mastertsa.jchueca.cities.domain.usecases.GetCityAndSightsUseCase
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class CityDetailViewModelFactory (private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val cityDao = CityDatabase.getDatabase(context).getDao()
        val repository = CityRepositoryImpl(context.dataStore, createService(), cityDao)
        val getCityAndSightsUseCase = GetCityAndSightsUseCase(repository)
        return CityDetailViewModel(getCityAndSightsUseCase) as T
    }
    
    
    private fun createService (): CityService {
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://ace-ripsaw-338222.oa.r.appspot.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        
        
        return retrofit.create(CityService::class.java)
    }
}