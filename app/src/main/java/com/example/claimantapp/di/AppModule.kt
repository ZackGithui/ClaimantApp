package com.example.claimantapp.di

import com.example.claimantapp.data.repository.AuthRepository
import com.example.claimantapp.data.repository.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
//Firebase instance
    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    //providing authRepository instance

    @Provides
    @Singleton
    fun provideRepositoryImpl(firebaseAuth : FirebaseAuth):AuthRepository{
        return AuthRepositoryImpl(firebaseAuth)
    }



}