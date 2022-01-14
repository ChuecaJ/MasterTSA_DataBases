package es.usj.mastertsa.jchueca.practice101.domain.usecases

import es.usj.mastertsa.jchueca.practice101.domain.PracticeRepository

class DeletePracticeData(private val practiceRepository: PracticeRepository) {
    fun deletePracticeData() {
        return practiceRepository.deletePracticeData()
    }
}
