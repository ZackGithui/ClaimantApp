package com.example.claimantapp.data

import com.example.claimantapp.util.Resource
import com.google.firebase.auth.FirebaseUser


interface AuthRepository {
    val currentUser:FirebaseUser?

   suspend fun loginUser(email:String,password:String): Resource<FirebaseUser>
    suspend fun registerUser(name:String,email:String,password:String):Resource<FirebaseUser>
    fun logOut()


}