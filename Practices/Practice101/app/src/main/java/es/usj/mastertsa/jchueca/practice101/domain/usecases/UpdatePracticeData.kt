package es.usj.mastertsa.jchueca.practice101.domain.usecases

import es.usj.mastertsa.jchueca.practice101.domain.PracticeData
import es.usj.mastertsa.jchueca.practice101.domain.PracticeRepository

class UpdatePracticeData(private val practiceRepository: PracticeRepository) {
    fun updatePracticeData(practiceData: PracticeData) {
        return practiceRepository.updatePracticeData(practiceData)
    }
}