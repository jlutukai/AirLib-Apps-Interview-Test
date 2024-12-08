package com.airlibs.domain.useCases

import com.airlibs.domain.models.data.MedicineData
import com.airlibs.domain.repositories.medicine.MedicineRepository
import kotlinx.coroutines.flow.Flow

class GetOneMedicineUseCase (
    private val repository: MedicineRepository
) {
    operator fun invoke(medicineId: Long?): Flow<MedicineData?> =
        repository.getMedicineById(medicineId)
}