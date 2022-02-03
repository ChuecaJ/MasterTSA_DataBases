package es.usj.mastertsa.jveron.ticketsdb.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.usj.mastertsa.jveron.ticketsdb.data.repository.TicketsRepositoryImpl
import es.usj.mastertsa.jveron.ticketsdb.data.repository.api.EventsService
import es.usj.mastertsa.jveron.ticketsdb.data.repository.dataStore
import es.usj.mastertsa.jveron.ticketsdb.data.repository.room.TicketsDatabase
import es.usj.mastertsa.jveron.ticketsdb.domain.usecases.UseCases
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class HomeViewModelFactory (private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val ticketsDao = TicketsDatabase.getDatabase(context).getDao()
        val repository = TicketsRepositoryImpl(context.dataStore, createService(), ticketsDao)
        val useCases = UseCases(repository)
        return HomeViewModel(useCases) as T
    }

    private fun createService() : EventsService {
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://apijveron20.000webhostapp.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        return retrofit.create(EventsService::class.java)
    }
}