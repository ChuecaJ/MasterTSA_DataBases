package es.usj.mastertsa.jchueca.practice101.domain.usecases

import es.usj.mastertsa.jchueca.practice101.domain.model.PracticeData
import es.usj.mastertsa.jchueca.practice101.domain.repository.PracticeRepository
import kotlinx.coroutines.flow.Flow

class GetPracticeData(private val practiceRepository: PracticeRepository) {
    fun getPracticeData(): Flow<PracticeData> {
        return practiceRepository.getPracticeData()
    }
}