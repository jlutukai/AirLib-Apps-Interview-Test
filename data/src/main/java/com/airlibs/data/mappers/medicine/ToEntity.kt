package com.airlibs.data.mappers.medicine

import android.util.Log
import com.airlibs.data.models.entities.MedicationEntity
import com.google.gson.Gson
import com.google.gson.JsonObject

fun String.mapJsonToMedicationList(): List<MedicationEntity> {
    val gson = Gson()
    val jsonObject = gson.fromJson(this, JsonObject::class.java)

    val medicationList = mutableListOf<MedicationEntity>()

    jsonObject.getAsJsonArray("problems").forEach { problem ->
        val problemName = problem.asJsonObject.keySet().iterator().next()

        problem.asJsonObject.getAsJsonArray(problemName).forEach { condition ->
            condition.asJsonObject.getAsJsonArray("medications").forEach { medication ->
                medication.asJsonObject.getAsJsonArray("medicationsClasses")
                    .forEach { medicationClass ->
                        medicationClass.asJsonObject.entrySet().forEach { entry ->
                            val className = entry.key
                            val associatedDrugs = entry.value.asJsonArray
                            associatedDrugs.forEach { drug ->
                                val drugObj = drug.asJsonObject
                                drugObj.keySet().forEach { associatedDrugKey->
                                    medicationList.add(
                                        MedicationEntity(
                                            problem = listOf(problemName),
                                            classes = listOf(className),
                                            name = getJsonObjectByPartialKey(
                                                drugObj,
                                                associatedDrugKey
                                            )?.get("name")?.asString,
                                            dose = getJsonObjectByPartialKey(
                                                drugObj,
                                                associatedDrugKey
                                            )?.get("dose")?.asString,
                                            strength = getJsonObjectByPartialKey(
                                                drugObj,
                                                associatedDrugKey
                                            )?.get("strength")?.asString,
                                            associatedDrug = associatedDrugKey
                                        )
                                    )
                                }
                            }
                        }
                    }
            }
        }
    }

    return medicationList
}

fun getJsonObjectByPartialKey(jsonObject: JsonObject, partialKey: String): JsonObject? {
    for (entry in jsonObject.entrySet()) {
        if (entry.key.equals(partialKey)) {
            return entry.value.asJsonArray.firstOrNull()?.asJsonObject
        }
    }
    return null
}