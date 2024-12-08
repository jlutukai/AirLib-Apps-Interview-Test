package com.airlibs.domain.repositories.auth

import com.airlibs.domain.models.data.UserDetails
import com.airlibs.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun saveUserDetails(userDetails: UserDetails): ResultWrapper<Long?>
    fun getUserDetails(userId: Int): Flow<UserDetails?>
}