package com.airlibs.app.di

import com.airlibs.app.features.dashboard.GetGreeting
import com.airlibs.app.features.dashboard.GetGreetingImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.Calendar
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

//    @Singleton
//    @Binds
//    abstract fun bindGreetingsManager(
//        greeting: GetGreetingImpl
//    ): GetGreeting
//
//    @Singleton
//    @Provides
//    fun providesCalendar(
//    ): Calendar {
//        return Calendar.getInstance()
//    }
}