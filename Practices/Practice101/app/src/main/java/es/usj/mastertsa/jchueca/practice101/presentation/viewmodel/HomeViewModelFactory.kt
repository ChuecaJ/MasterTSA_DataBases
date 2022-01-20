package es.usj.mastertsa.jchueca.practice101.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.usj.mastertsa.jchueca.practice101.data.repository.PracticeDataRepositoryImpl
import es.usj.mastertsa.jchueca.practice101.data.sharedpreferences.PracticeDataSharedPreferences
import es.usj.mastertsa.jchueca.practice101.domain.usecases.AddPracticeData
import es.usj.mastertsa.jchueca.practice101.domain.usecases.DeletePracticeData
import es.usj.mastertsa.jchueca.practice101.domain.usecases.GetPracticeData
import es.usj.mastertsa.jchueca.practice101.domain.usecases.UpdatePracticeData

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(private val context: Context) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val practiceDataSharedPreferences = PracticeDataSharedPreferences(context)
        val practiceDataRepositoryImpl =
            PracticeDataRepositoryImpl(practiceDataSharedPreferences)
        return HomeViewModel(
            getPracticeData = GetPracticeData(practiceDataRepositoryImpl),
            addPracticeData = AddPracticeData(practiceDataRepositoryImpl),
            deletePracticeData = DeletePracticeData(practiceDataRepositoryImpl),
            updatePracticeData = UpdatePracticeData(practiceDataRepositoryImpl)
        ) as T
    }
}
