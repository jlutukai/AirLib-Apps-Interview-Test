package com.airlibs.data.sources.local

import androidx.room.withTransaction
import com.airlibs.data.models.entities.MedicationEntity
import com.airlibs.data.models.entities.UserDetailsEntity
import com.airlibs.data.sources.local.daos.auth.UserDao
import com.airlibs.data.sources.local.daos.medications.MedicationDao
import com.airlibs.data.sources.local.database.AirLibsDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val db: AirLibsDatabase,
    private val userDao: UserDao,
    private val medicationDao: MedicationDao

) : LocalDataSource {
    override suspend fun saveUserDetails(userDetailsEntity: UserDetailsEntity): Long =
        db.withTransaction {
            userDao.deleteAll()
            userDao.insert(userDetailsEntity)
        }


    override fun getCurrentUser(userId: Int): Flow<UserDetailsEntity?> =
        userDao.getUserDetails(userId = userId)

    override suspend fun upsertMedicines(medicines: List<MedicationEntity>) =
        db.withTransaction {
            medicationDao.deleteAll()
            medicationDao.insert(medicines)
        }

    override fun getMedicines(): Flow<List<MedicationEntity>> =
        medicationDao.getMedicines()

    override fun getMedicineById(id: Long?): Flow<MedicationEntity?> =
        medicationDao.getMedicineById(id)
}

