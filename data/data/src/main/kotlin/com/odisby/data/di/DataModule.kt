package com.odisby.data.di

import com.odisby.copa.womens.domain.repositories.MatchesRepository
import com.odisby.data.repository.MatchesRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface DataModule {
    @Binds
    fun providesMatchesRepository(impl: MatchesRepositoryImpl): MatchesRepository
}