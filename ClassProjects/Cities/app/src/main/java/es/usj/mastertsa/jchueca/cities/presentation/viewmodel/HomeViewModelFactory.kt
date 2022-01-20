package es.usj.mastertsa.jchueca.cities.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.usj.mastertsa.jchueca.cities.data.repository.CITIES_PREFERENCE
import es.usj.mastertsa.jchueca.cities.data.repository.CityRepositoryImpl
import es.usj.mastertsa.jchueca.cities.data.repository.api.CityService
import es.usj.mastertsa.jchueca.cities.data.repository.dataStore
import es.usj.mastertsa.jchueca.cities.data.repository.room.CityDao
import es.usj.mastertsa.jchueca.cities.data.repository.room.CityDatabase
import es.usj.mastertsa.jchueca.cities.data.repository.slite.CitySqliteHelper
import es.usj.mastertsa.jchueca.cities.domain.usecases.AddCityUseCase
import es.usj.mastertsa.jchueca.cities.domain.usecases.GetCitiesUseCase
import es.usj.mastertsa.jchueca.cities.domain.usecases.GetFilterUseCase
import es.usj.mastertsa.jchueca.cities.domain.usecases.SetFilterUseCase
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class HomeViewModelFactory (private val context: Context) : ViewModelProvider.Factory {
    
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val cityDao = CityDatabase.getDatabase(context).getDao()
        val sqliteHelper = CitySqliteHelper(context)
        val repository = CityRepositoryImpl(context.dataStore, sqliteHelper, createService(), cityDao)
        val addCityUseCase = AddCityUseCase(repository)
        val getCitiesUseCase = GetCitiesUseCase(repository)
        val setFilterUseCase = SetFilterUseCase(repository)
        val getFilterUseCase = GetFilterUseCase(repository)
        return HomeViewModel(addCityUseCase, getCitiesUseCase, setFilterUseCase, getFilterUseCase) as T
    }
    
    private fun createService (): CityService{
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://ace-ripsaw-338222.oa.r.appspot.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        
        
        return retrofit.create(CityService::class.java)
    }
}