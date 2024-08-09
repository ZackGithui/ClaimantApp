package com.example.claimantapp.presentation.profile

import androidx.lifecycle.ViewModel
import com.example.claimantapp.data.AuthRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val authRepository: AuthRepository):ViewModel() {

    val currentUser:FirebaseUser? get() = authRepository.currentUser

    fun logout(){
        authRepository.logOut()
    }

}