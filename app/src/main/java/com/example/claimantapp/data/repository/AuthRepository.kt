package com.example.claimantapp.data.repository

import com.example.claimantapp.util.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow


interface AuthRepository {
    val currentUser:FirebaseUser?

   suspend fun loginUser(email:String,password:String): Resource<FirebaseUser>
    suspend fun registerUser(name:String,email:String,password:String):Resource<FirebaseUser>
    fun logOut()


}