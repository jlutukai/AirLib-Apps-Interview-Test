package com.airlibs.data.mappers.medicine

import com.airlibs.data.models.entities.MedicationEntity
import com.airlibs.domain.models.data.MedicineData

//return only need fields
fun MedicationEntity.toDomain(): MedicineData = MedicineData(
    id = id?:0,
    name = name?:"",
    dose = dose?:"",
    strength = strength?:""
)