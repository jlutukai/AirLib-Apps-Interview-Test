package com.airlibs.data.sources.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.airlibs.data.models.entities.MedicationEntity
import com.airlibs.data.models.entities.UserDetailsEntity
import com.airlibs.data.sources.local.daos.auth.UserDao
import com.airlibs.data.sources.local.daos.medications.MedicationDao
import com.airlibs.data.sources.local.typeConverters.StringListTypeConverter

@Database(
    entities = [
        MedicationEntity::class, UserDetailsEntity::class
    ],
    version = 1,
    exportSchema = true,
)

@TypeConverters( StringListTypeConverter::class)
abstract class AirLibsDatabase : RoomDatabase() {
    abstract fun medicationDao(): MedicationDao
    abstract fun userDao(): UserDao
}