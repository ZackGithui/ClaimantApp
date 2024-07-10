package com.example.claimantapp.data.repository

import com.example.claimantapp.util.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

 class AuthRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth): AuthRepository {
     override val currentUser: FirebaseUser?
         get() = firebaseAuth.currentUser

     override suspend fun loginUser(email: String, password: String): Resource<FirebaseUser> {
        return try {
            val result=firebaseAuth.signInWithEmailAndPassword(email,password).await()
            Resource.Success(result.user!!)


        }catch (e:Exception){
            e.printStackTrace()
            Resource.Error(e.message)

        }




        }
    override suspend fun  registerUser(name:String,email: String, password: String): Resource<FirebaseUser> {

        return try{

            val result= firebaseAuth.createUserWithEmailAndPassword(email,password).await()
            result?.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build())?.await()
            Resource.Success(result.user)
        }
        catch(e:Exception) {
            e.printStackTrace()
            Resource.Error(e.message)
        }

    }

    override fun logOut() {
        firebaseAuth.signOut()
    }
  }


