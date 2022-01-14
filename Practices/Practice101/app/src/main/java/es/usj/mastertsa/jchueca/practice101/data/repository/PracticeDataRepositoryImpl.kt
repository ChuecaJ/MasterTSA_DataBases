package es.usj.mastertsa.jchueca.practice101.data.repository

import es.usj.mastertsa.jchueca.practice101.domain.model.PracticeData
import es.usj.mastertsa.jchueca.practice101.domain.repository.PracticeRepository

class PracticeDataRepositoryImpl : PracticeRepository {
    override fun getPracticeData(): PracticeData {
        return PracticeData("Práctica 03")
    }
    override fun addPracticeData(practiceData: PracticeData) {
    }
    override fun deletePracticeData() {
    }
    override fun updatePracticeData(practiceData: PracticeData) {
    }
}