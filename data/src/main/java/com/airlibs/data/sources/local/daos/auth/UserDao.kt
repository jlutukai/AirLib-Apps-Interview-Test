package com.airlibs.data.sources.local.daos.auth

import androidx.room.Dao
import androidx.room.Query
import com.airlibs.data.models.entities.UserDetailsEntity
import com.airlibs.data.models.entities.UserDetailsEntity.Companion.USER_TABLE_NAME
import com.airlibs.data.sources.local.daos.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao : BaseDao<UserDetailsEntity> {
    @Query("DELETE FROM $USER_TABLE_NAME ")
    suspend fun deleteAll()

    @Query("SELECT * FROM $USER_TABLE_NAME where id =:userId")
    fun getUserDetails(userId: Int): Flow<UserDetailsEntity?>
}