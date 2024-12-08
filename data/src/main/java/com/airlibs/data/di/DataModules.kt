package com.airlibs.data.di

import android.content.Context
import android.content.SharedPreferences
import com.airlibs.data.sources.local.database.AirLibsDatabase
import com.airlibs.data.utils.Constants.PREFS_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModules {
    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun providesMedicationDao(db: AirLibsDatabase) =
        db.medicationDao()

    @Provides
    @Singleton
    fun providesUsrDao(db: AirLibsDatabase) =
        db.userDao()

}