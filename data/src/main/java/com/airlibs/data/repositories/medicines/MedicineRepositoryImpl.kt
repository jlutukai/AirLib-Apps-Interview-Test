package com.airlibs.data.repositories.medicines

import com.airlibs.data.mappers.medicine.mapJsonToMedicationList
import com.airlibs.data.mappers.medicine.toDomain
import com.airlibs.data.sources.local.LocalDataSource
import com.airlibs.data.sources.remote.RemoteDataSource
import com.airlibs.data.utils.flowSafeCall
import com.airlibs.domain.di.AirLibsDispatchers
import com.airlibs.domain.di.Dispatcher
import com.airlibs.domain.models.data.MedicineData
import com.airlibs.domain.repositories.medicine.MedicineRepository
import com.airlibs.domain.utils.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MedicineRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    @Dispatcher(AirLibsDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : MedicineRepository {
    override suspend fun getMedicinesRemote(): Flow<ResultWrapper<Boolean>> =
        flowSafeCall(ioDispatcher) {
            val response = remoteDataSource.getMedicines()
            val data = response.body()
            //convert raw json data from response to medicine model, in a scalable way
            localDataSource.upsertMedicines(
                data?.string()?.mapJsonToMedicationList() ?: emptyList()
            )
            response.isSuccessful
        }

    override fun getMedicines(): Flow<List<MedicineData>> {
        return localDataSource.getMedicines().map { flow-> flow.map { it.toDomain() } }
    }

    override fun getMedicineById(medicineId: Long?): Flow<MedicineData?> {
        return localDataSource.getMedicineById(medicineId).map { it?.toDomain() }
    }
}