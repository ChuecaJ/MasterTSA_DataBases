package es.usj.mastertsa.jchueca.practice101.domain.repository

import es.usj.mastertsa.jchueca.practice101.domain.model.PracticeData
import kotlinx.coroutines.flow.Flow

interface PracticeRepository {
    
    fun getPracticeData(): Flow<PracticeData>
    
    suspend fun addPracticeData(practiceData: PracticeData)
    
    suspend fun deletePracticeData()
    
    suspend fun updatePracticeData(practiceData: PracticeData)
}