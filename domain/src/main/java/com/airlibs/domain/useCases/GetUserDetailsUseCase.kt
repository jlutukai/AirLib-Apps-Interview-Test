package com.airlibs.domain.useCases

import com.airlibs.domain.models.data.UserDetails
import com.airlibs.domain.repositories.auth.AuthRepository
import kotlinx.coroutines.flow.Flow

class GetUserDetailsUseCase (
    private val repository: AuthRepository
) {
     operator fun invoke(userId: Int): Flow<UserDetails?> =
        repository.getUserDetails(userId)
}