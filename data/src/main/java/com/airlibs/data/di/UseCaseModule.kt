package com.airlibs.data.di

import com.airlibs.domain.repositories.auth.AuthRepository
import com.airlibs.domain.repositories.medicine.MedicineRepository
import com.airlibs.domain.useCases.GetMedicinesUseCase
import com.airlibs.domain.useCases.GetOneMedicineUseCase
import com.airlibs.domain.useCases.GetUserDetailsUseCase
import com.airlibs.domain.useCases.SaveUserDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @ViewModelScoped
    @Provides
    fun providesSaveUserUseCase(
        repository: AuthRepository
    ): SaveUserDetailsUseCase = SaveUserDetailsUseCase(repository)

    @ViewModelScoped
    @Provides
    fun providesGetCurrentUserUseCase(
        repository: AuthRepository
    ): GetUserDetailsUseCase = GetUserDetailsUseCase(repository)

    @ViewModelScoped
    @Provides
    fun providesGetMedicinesUseCase(
        repository: MedicineRepository
    ): GetMedicinesUseCase = GetMedicinesUseCase(repository)

    @ViewModelScoped
    @Provides
    fun providesGetMedicineByIdUseCase(
        repository: MedicineRepository
    ): GetOneMedicineUseCase = GetOneMedicineUseCase(repository)

}