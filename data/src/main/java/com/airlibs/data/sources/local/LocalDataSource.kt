package com.airlibs.data.sources.local

import com.airlibs.data.models.entities.MedicationEntity
import com.airlibs.data.models.entities.UserDetailsEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource{
    suspend fun saveUserDetails(userDetailsEntity: UserDetailsEntity): Long?
    fun getCurrentUser(userId: Int): Flow<UserDetailsEntity?>
    suspend fun upsertMedicines(medicines: List<MedicationEntity>)
    fun getMedicines(): Flow<List<MedicationEntity>>
    fun getMedicineById(id: Long?): Flow<MedicationEntity?>
}