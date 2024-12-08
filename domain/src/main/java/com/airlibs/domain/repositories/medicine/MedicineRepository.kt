package com.airlibs.domain.repositories.medicine

import com.airlibs.domain.models.data.MedicineData
import com.airlibs.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface MedicineRepository {
    suspend fun getMedicinesRemote(): Flow<ResultWrapper<Boolean>>

    fun getMedicines(): Flow<List<MedicineData>>

    fun getMedicineById(medicineId: Long?): Flow<MedicineData?>
}