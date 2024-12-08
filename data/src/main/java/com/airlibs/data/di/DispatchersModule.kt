package com.airlibs.data.di

import com.airlibs.domain.di.AirLibsDispatchers
import com.airlibs.domain.di.Dispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @Provides
    @Dispatcher(AirLibsDispatchers.IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Dispatcher(AirLibsDispatchers.MAIN)
    fun providesMAINDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Dispatcher(AirLibsDispatchers.DEFAULT)
    fun providesDEFAULTDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @Dispatcher(AirLibsDispatchers.UNCONFINED)
    fun providesUNCONFINEDDispatcher(): CoroutineDispatcher = Dispatchers.Unconfined
}