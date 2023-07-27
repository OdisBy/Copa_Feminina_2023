package com.odisby.copa_feminina.di

import android.app.Application
import android.content.Context
import com.odisby.copa.womens.domain.usecase.GetMatchesUseCase
import com.odisby.copa_feminina.data.remote.di.NetworkModule
import com.odisby.copa_feminina.data.remote.di.RemoteModule
import com.odisby.copa_feminina.data.remote.di.ServiceModule
import com.odisby.data.di.DataModule
import com.odisby.local.di.LocalModule
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(
    includes = [
        DataModule::class,
        LocalModule::class,
        RemoteModule::class,
        NetworkModule::class,
        ServiceModule::class,
    ]
)
@InstallIn(SingletonComponent::class)
abstract class ApplicationModule {
    @Binds
    abstract fun bindContext(application: Application): Context
}
