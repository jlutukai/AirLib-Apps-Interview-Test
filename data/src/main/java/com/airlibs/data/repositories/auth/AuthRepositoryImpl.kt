package com.airlibs.data.repositories.auth

import com.airlibs.data.mappers.auth.toDomain
import com.airlibs.data.mappers.auth.toEntity
import com.airlibs.data.sources.local.LocalDataSource
import com.airlibs.data.sources.local.dataStore.AirLibsDataStore
import com.airlibs.domain.models.data.UserDetails
import com.airlibs.domain.models.data.utils.UiText
import com.airlibs.domain.repositories.auth.AuthRepository
import com.airlibs.domain.utils.Constants.USER_ID_KEY
import com.airlibs.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val dataStore: AirLibsDataStore
) : AuthRepository {
    override suspend fun saveUserDetails(userDetails: UserDetails): ResultWrapper<Long?> = try {
        val userId = localDataSource.saveUserDetails(userDetails.toEntity())
        dataStore.putInt(USER_ID_KEY, userId!!.toInt())
        ResultWrapper.Success(userId)
    } catch (e: Exception) {
        ResultWrapper.GenericError(
            errorMessage = UiText.DynamicString(
                e.message ?: "Failed to save user details"
            )
        )
    }

    override fun getUserDetails(userId: Int): Flow<UserDetails?> {
        return localDataSource.getCurrentUser(userId).map { it?.toDomain() }
    }
}