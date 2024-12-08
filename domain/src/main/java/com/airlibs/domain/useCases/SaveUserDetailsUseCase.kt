package com.airlibs.domain.useCases

import com.airlibs.domain.models.data.UserDetails
import com.airlibs.domain.repositories.auth.AuthRepository
import com.airlibs.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

class SaveUserDetailsUseCase (
    private val repository: AuthRepository
) {
    suspend operator fun invoke(userDetails: UserDetails): ResultWrapper<Long?> =
        repository.saveUserDetails(userDetails)
}