package com.airlibs.data.di

import com.airlibs.domain.di.AirLibsDispatchers
import com.airlibs.domain.di.Dispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@Module
@InstallIn(SingletonComponent::class)
object CoroutineScopeModule {
    @AppCoroutineScope
    @Provides
    fun provideApplicationLevelCoroutineScope(
        @Dispatcher(AirLibsDispatchers.IO) ioDispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(ioDispatcher + SupervisorJob())
}