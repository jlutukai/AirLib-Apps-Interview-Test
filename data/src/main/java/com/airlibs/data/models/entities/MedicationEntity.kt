package com.airlibs.data.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.airlibs.data.models.entities.MedicationEntity.Companion.MEDICATION_TABLE_NAME

@Entity(MEDICATION_TABLE_NAME)
data class MedicationEntity(
    @PrimaryKey(autoGenerate = true) val id : Long? = null,
    val problem:List<String>,
    val classes:List<String>,
    val name: String?,
    val dose: String?,
    val strength: String?,
    val associatedDrug: String
){
    companion object{
        const val MEDICATION_TABLE_NAME = "medication_table"
    }
}
