package com.odisby.copa_feminina.data.remote.di

import com.odisby.copa_feminina.data.remote.source.MatchDataSourceRemote
import com.odisby.data.source.MatchesDataSource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RemoteModule {
    @Binds
    abstract fun providesMatchDataSourceRemote(impl: MatchDataSourceRemote): MatchesDataSource.Remote
}