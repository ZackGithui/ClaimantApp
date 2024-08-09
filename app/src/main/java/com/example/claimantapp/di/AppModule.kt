package com.example.claimantapp.di

import com.example.claimantapp.data.AuthRepository
import com.example.claimantapp.data.AuthRepositoryImpl
import com.example.claimantapp.data.UfaaAPI
import com.google.firebase.auth.FirebaseAuth
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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
    fun provideRepositoryImpl(firebaseAuth : FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }

    @Provides
    @Singleton

    fun provideApi():UfaaAPI{
        val moshi:Moshi=Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return(
                Retrofit.Builder()
                    .baseUrl(UfaaAPI.BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build()
                    .create(UfaaAPI::class.java)


                )

    }



}