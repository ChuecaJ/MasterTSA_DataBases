package es.usj.mastertsa.jchueca.cities.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.usj.mastertsa.jchueca.cities.data.repository.CityRepositoryImpl
import es.usj.mastertsa.jchueca.cities.domain.usecases.AddCityUseCase
import es.usj.mastertsa.jchueca.cities.domain.usecases.GetCitiesUseCase

class HomeViewModelFactory: ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = CityRepositoryImpl()
        val addCity = AddCityUseCase(repository)
        val getCities = GetCitiesUseCase(repository)
        return HomeViewModel(addCity, getCities) as T
    }
}