package es.usj.mastertsa.jchueca.practice101.data.repository

import es.usj.mastertsa.jchueca.practice101.data.sharedpreferences.PracticeDataSharedPreferences
import es.usj.mastertsa.jchueca.practice101.domain.model.PracticeData
import es.usj.mastertsa.jchueca.practice101.domain.repository.PracticeRepository

class PracticeDataRepositoryImpl(private val practiceDataSharedPref:
                                 PracticeDataSharedPreferences
) : PracticeRepository {
    override fun getPracticeData(): PracticeData {
        val name = practiceDataSharedPref.getPrefData()
        return PracticeData(name)
    }
    override fun addPracticeData(practiceData: PracticeData) {
        practiceDataSharedPref.addPrefData(practiceData.name)
    }
    override fun deletePracticeData() {
        practiceDataSharedPref.deletePrefData()
    }
    override fun updatePracticeData(practiceData: PracticeData) {
        practiceDataSharedPref.updatePrefData(practiceData.name)
    }
}