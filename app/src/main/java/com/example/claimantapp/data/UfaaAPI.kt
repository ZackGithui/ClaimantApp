package com.example.claimantapp.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UfaaAPI {

@GET("name/{name}")
suspend fun getClaimantByName(@Path("name") name:String):List<ClaimantDataItem>

@GET("id/{idNumber}")
suspend fun getClaimantByIdNumber(@Path("idNumber") idNumber:String):List<ClaimantDataItem>

@GET("postalAddress/{postalAddress}")
suspend fun getClaimantPostalAddress(@Path("postalAddress")postalAddress:String):ClaimantData

companion object{
    val BASE_URL="http://192.168.40.117:3000/ufaa/"
}


}