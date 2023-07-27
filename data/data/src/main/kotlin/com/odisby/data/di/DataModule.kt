package com.odisby.data.di

import com.odisby.copa.womens.domain.repositories.MatchesRepository
import com.odisby.data.repository.MatchesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun providesMatchesRepository(impl: MatchesRepositoryImpl): MatchesRepository
}