package com.airlibs.data.di


import com.airlibs.data.repositories.auth.AuthRepositoryImpl
import com.airlibs.data.repositories.medicines.MedicineRepositoryImpl
import com.airlibs.domain.repositories.auth.AuthRepository
import com.airlibs.domain.repositories.medicine.MedicineRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {


    @Singleton
    @Binds
    fun bindsAuthRepository(
        repository: AuthRepositoryImpl
    ): AuthRepository

    @Singleton
    @Binds
    fun bindsMedicineRepository(
        repository: MedicineRepositoryImpl
    ): MedicineRepository
}