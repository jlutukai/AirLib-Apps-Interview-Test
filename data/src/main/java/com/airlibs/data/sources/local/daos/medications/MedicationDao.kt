package com.airlibs.data.sources.local.daos.medications

import androidx.room.Dao
import androidx.room.Query
import com.airlibs.data.models.entities.MedicationEntity
import com.airlibs.data.models.entities.MedicationEntity.Companion.MEDICATION_TABLE_NAME
import com.airlibs.data.sources.local.daos.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicationDao : BaseDao<MedicationEntity> {
    @Query("DELETE FROM $MEDICATION_TABLE_NAME")
    suspend fun deleteAll()

    @Query("SELECT * FROM $MEDICATION_TABLE_NAME")
    fun getMedicines(): Flow<List<MedicationEntity>>

    @Query("SELECT * FROM $MEDICATION_TABLE_NAME where id =:medicineId")
    fun getMedicineById(medicineId: Long?): Flow<MedicationEntity?>
}