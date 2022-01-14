package es.usj.mastertsa.jchueca.practice101.domain.usecases

import es.usj.mastertsa.jchueca.practice101.domain.PracticeData
import es.usj.mastertsa.jchueca.practice101.domain.PracticeRepository

class AddPracticeData(private val practiceRepository: PracticeRepository) {
    fun addPracticeData(practiceData: PracticeData) {
        return practiceRepository.addPracticeData(practiceData)
    }
}