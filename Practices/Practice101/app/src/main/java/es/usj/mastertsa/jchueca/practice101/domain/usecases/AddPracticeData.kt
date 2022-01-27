package es.usj.mastertsa.jchueca.practice101.domain.usecases

import es.usj.mastertsa.jchueca.practice101.domain.model.PracticeData
import es.usj.mastertsa.jchueca.practice101.domain.repository.PracticeRepository

class AddPracticeData(private val practiceRepository: PracticeRepository) {
    suspend fun addPracticeData(practiceData: PracticeData) {
        return practiceRepository.addPracticeData(practiceData)
    }
}