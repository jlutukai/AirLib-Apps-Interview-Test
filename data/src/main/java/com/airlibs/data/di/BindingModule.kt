package com.airlibs.data.di

import com.airlibs.data.sources.local.LocalDataSource
import com.airlibs.data.sources.local.LocalDataSourceImpl
import com.airlibs.data.sources.remote.RemoteDataSource
import com.airlibs.data.sources.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindingModule {

    @Singleton
    @Binds
    abstract fun provideRemoteDataSource(
        remoteDataSourceImpl: RemoteDataSourceImpl
    ): RemoteDataSource

    @Singleton
    @Binds
    abstract fun bindLocalDataSource(
        localDataSourceImpl: LocalDataSourceImpl
    ): LocalDataSource


}