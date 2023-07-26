package com.odisby.copa_feminina.data.remote.di

import com.odisby.copa_feminina.data.remote.services.MatchesServices
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
class ServiceModule {
    @Provides
    fun provideAuthService(retrofit: Retrofit) = retrofit.create<MatchesServices>()
}