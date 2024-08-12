package com.example.claimantapp.domain

import com.example.claimantapp.data.ClaimantDataItem
import com.example.claimantapp.util.Resource
import kotlinx.coroutines.flow.Flow

 interface Repository {

    suspend fun getClaimantByName(name:String): Flow<Resource<List<ClaimantDataItem>>>

    suspend fun getClaimantByIdNumber(idNumber:String): Flow<Resource<List<ClaimantDataItem>>>

    suspend fun getClaimantByPostalAddress(postalAddress:String): Flow<Resource<List<ClaimantDataItem>>>
}