package com.airlibs.data.di

import android.content.Context
import androidx.room.Room
import com.airlibs.data.sources.local.database.AirLibsDatabase
import com.airlibs.data.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AirLibsDatabase {
        return Room.databaseBuilder(
            appContext,
            AirLibsDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}