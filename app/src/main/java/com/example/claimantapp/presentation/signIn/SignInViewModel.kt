package com.example.claimantapp.presentation.signIn

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class SignInViewModel:ViewModel() {
    private val _state= MutableStateFlow(SignInState())
    val signInState= _state.asStateFlow()

    fun onEmailChange(value:String){
        _state.update {
            it.copy(email = value)
        }
    }
    fun onPasswordChange(value:String){
        _state.update {
            it.copy(password = value)
        }
    }

    fun viewPassword(){
        _state.update {
            it.copy(
                viewPassword = !_state.value.viewPassword
            )
        }
    }
}