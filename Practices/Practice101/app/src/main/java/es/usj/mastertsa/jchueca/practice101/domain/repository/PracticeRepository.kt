package es.usj.mastertsa.jchueca.practice101.domain.repository

import es.usj.mastertsa.jchueca.practice101.domain.model.PracticeData

interface PracticeRepository {
    fun getPracticeData(): PracticeData
    fun addPracticeData(practiceData: PracticeData)
    fun deletePracticeData()
    fun updatePracticeData(practiceData: PracticeData)
}