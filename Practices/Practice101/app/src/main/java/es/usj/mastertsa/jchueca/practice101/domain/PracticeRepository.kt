package es.usj.mastertsa.jchueca.practice101.domain

interface PracticeRepository {
    fun getPracticeData(): PracticeData
    fun addPracticeData(practiceData: PracticeData)
    fun deletePracticeData()
    fun updatePracticeData(practiceData: PracticeData)
}