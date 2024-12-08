package com.airlibs.domain.utils

import com.airlibs.domain.models.data.utils.UiText
import com.airlibs.domain.models.response.common.ErrorResponse

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class GenericError(
        val code: Int? = null,
        val errorResponse: ErrorResponse? = null,
        val errorMessage: UiText
    ) : ResultWrapper<Nothing>()
    data object Loading : ResultWrapper<Nothing>()
}

data class Medication(
    val problem:List<String>,
    val classes:List<String>,
    val name: String,
    val dose: String,
    val strength: String,
    val associatedDrug: Int
)


//{
//    "problems": [{
//    "Diabetes":[{
//    "medications":[{
//    "medicationsClasses":[{
//    "className":[{
//    "associatedDrug":[{
//    "name":"asprin",
//    "dose":"",
//    "strength":"500 mg"
//}],
//    "associatedDrug#2":[{
//    "name":"somethingElse",
//    "dose":"",
//    "strength":"500 mg"
//}]
//}],
//    "className2":[{
//    "associatedDrug":[{
//    "name":"asprin",
//    "dose":"",
//    "strength":"500 mg"
//}],
//    "associatedDrug#2":[{
//    "name":"somethingElse",
//    "dose":"",
//    "strength":"500 mg"
//}]
//}]
//}]
//}],
//    "labs":[{
//    "missing_field": "missing_value"
//}]
//}],
//    "Asthma":[{}]
//}]}