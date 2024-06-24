package com.example.claimantapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "SignUp")
data class UserData(

    val email:String,
    val username:String,
    val password:String,
    val confirmPassword:String,
    @PrimaryKey (autoGenerate = true)
    val id:Int=0,
)
