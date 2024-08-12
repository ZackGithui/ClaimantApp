package com.example.claimantapp.data

import android.content.ContentValues.TAG
import android.util.Log
import com.example.claimantapp.domain.Repository
import com.example.claimantapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RepositoryImple  @Inject constructor(private val api: UfaaAPI): Repository {
    override suspend fun getClaimantByName(name: String): Flow<Resource<List<ClaimantDataItem>>> =
        flow{
            emit(Resource.Loading())
            try {
                Log.d(TAG, "Fetching top headlines for category: $name")
                val response=api.getClaimantByName(name=name)
                Log.d(TAG,"fetched: $response")
                emit(Resource.Success(response))
            }
            catch (e:HttpException){
                emit(Resource.Error(e.localizedMessage ?:"Unexpected error occurred"))
               // Log.e(TAG, "Error fetching data: ${e.localizedMessage}")

            }
            catch (e:IOException){
                emit(Resource.Error("Couldn't reach the server. Please check your internet connection")
                )
                Log.e(TAG, "Error fetching data: ${e.localizedMessage}")

            }


    }

    override suspend fun getClaimantByIdNumber(idNumber: String): Flow<Resource<List<ClaimantDataItem>>> = flow{
        emit(Resource.Loading())
        try {
            val responses=api.getClaimantByIdNumber(idNumber)
            emit(Resource.Success(responses))
        }
        catch (e:HttpException){
            emit(Resource.Error(e.localizedMessage ?:"Unexpected error occurred"))
        }
        catch (e:IOException){
            emit(Resource.Error("Couldn't reach the server. Please check your internet connection"))
        }


    }




    override suspend fun getClaimantByPostalAddress(postalAddress: String): Flow<Resource<List<ClaimantDataItem>>> = flow{
        emit(Resource.Loading())
        try {
            val response=api.getClaimantPostalAddress(postalAddress = postalAddress)
            emit(Resource.Success(response))
        }
        catch (e:HttpException){
            emit(Resource.Error(e.localizedMessage ?:"Unexpected error occurred"))
        }
        catch (e:IOException){
            emit(Resource.Error("Couldn't reach the server. Please check your internet connection"))
        }


    }


    }

