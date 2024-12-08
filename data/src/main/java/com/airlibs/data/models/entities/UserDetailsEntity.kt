package com.airlibs.data.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.airlibs.data.models.entities.UserDetailsEntity.Companion.USER_TABLE_NAME

@Entity(tableName = USER_TABLE_NAME)
data class UserDetailsEntity(
    @PrimaryKey(autoGenerate = true) val id : Long? = null,
    val email: String
){
    companion object{
        const val USER_TABLE_NAME = "user_details_table"
    }
}
