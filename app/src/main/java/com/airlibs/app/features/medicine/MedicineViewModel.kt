package com.airlibs.app.features.medicine

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.airlibs.app.features.app.Medicine
import com.airlibs.data.sources.local.dataStore.AirLibsDataStore
import com.airlibs.domain.models.data.MedicineData
import com.airlibs.domain.repositories.medicine.MedicineRepository
import com.airlibs.domain.useCases.GetMedicinesUseCase
import com.airlibs.domain.useCases.GetOneMedicineUseCase
import com.airlibs.domain.useCases.GetUserDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class MedicineViewModel @Inject constructor(
    private val getOneMedicineUseCase: GetOneMedicineUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {


     fun getMedicine() : Flow<MedicineData?> {
        val medicineId: Long? = savedStateHandle.get<Long>("id")
        return getOneMedicineUseCase(medicineId = medicineId?:0)
    }
}