package com.example.claimantapp.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ClaimantDataItem(
    @Json(name = "Amount Due to Owner")
    val amountDueToOwner: Double,
    @Json(name = "Holder Name")
    val holderName: String,
    @Json(name = "ID Number")
    val iDNumber: String,
    @Json(name = "Name")
    val name: String,
    @Json(name = "Owners Postal Address")
    val ownersPostalAddress: String
)