package com.odisby.copa_feminina.data.remote.di

import com.odisby.copa_feminina.data.remote.source.MatchDataSourceRemote
import com.odisby.data.source.MatchesDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RemoteModule {
    @Binds
    abstract fun providesMatchDataSourceRemote(impl: MatchDataSourceRemote): MatchesDataSource.Remote
}