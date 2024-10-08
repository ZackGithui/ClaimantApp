package com.example.claimantapp.di

import com.example.claimantapp.data.RepositoryImple
import com.example.claimantapp.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRepository(
        claimantRepository:RepositoryImple

    ):Repository


}