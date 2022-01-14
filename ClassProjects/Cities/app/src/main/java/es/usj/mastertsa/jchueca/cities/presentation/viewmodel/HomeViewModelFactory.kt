package es.usj.mastertsa.jchueca.cities.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.usj.mastertsa.jchueca.cities.data.repository.CITIES_PREFERENCE
import es.usj.mastertsa.jchueca.cities.data.repository.CityRepositoryImpl
import es.usj.mastertsa.jchueca.cities.domain.usecases.AddCityUseCase
import es.usj.mastertsa.jchueca.cities.domain.usecases.GetCitiesUseCase
import es.usj.mastertsa.jchueca.cities.domain.usecases.GetFilterUseCase
import es.usj.mastertsa.jchueca.cities.domain.usecases.SetFilterUseCase

class HomeViewModelFactory(private val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = CityRepositoryImpl(context.getSharedPreferences(CITIES_PREFERENCE, Context.MODE_PRIVATE))
        val addCityUseCase = AddCityUseCase(repository)
        val getCitiesUseCase = GetCitiesUseCase(repository)
        val setFilterUseCase = SetFilterUseCase(repository)
        val getFilterUseCase = GetFilterUseCase(repository)
        return HomeViewModel(addCityUseCase, getCitiesUseCase, setFilterUseCase, getFilterUseCase) as T
    }
}