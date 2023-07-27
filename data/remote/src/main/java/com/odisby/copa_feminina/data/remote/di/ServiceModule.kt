package com.odisby.copa_feminina.data.remote.di

import com.odisby.copa_feminina.data.remote.services.MatchesServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {
    @Provides
    fun provideAuthService(retrofit: Retrofit) = retrofit.create<MatchesServices>()
}