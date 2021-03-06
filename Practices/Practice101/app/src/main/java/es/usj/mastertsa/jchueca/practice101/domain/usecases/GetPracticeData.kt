package es.usj.mastertsa.jchueca.practice101.domain.usecases

import es.usj.mastertsa.jchueca.practice101.domain.model.PracticeData
import es.usj.mastertsa.jchueca.practice101.domain.repository.PracticeRepository

class GetPracticeData(private val practiceRepository: PracticeRepository) {
    fun getPracticeData(): PracticeData {
        return practiceRepository.getPracticeData()
    }
}