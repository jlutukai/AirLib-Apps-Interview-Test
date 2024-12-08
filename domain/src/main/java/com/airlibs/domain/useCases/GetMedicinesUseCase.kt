package com.airlibs.domain.useCases

import com.airlibs.domain.models.data.MedicineData
import com.airlibs.domain.repositories.medicine.MedicineRepository
import kotlinx.coroutines.flow.Flow

class GetMedicinesUseCase(
    private val repository: MedicineRepository
) {
    operator fun invoke(): Flow<List<MedicineData>> =
        repository.getMedicines()
}