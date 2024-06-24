package com.example.claimantapp.presentation.signUp


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

 class SignUpViewModel:ViewModel() {

    private val _state= MutableStateFlow(SignUpStates())
    val signUpState= _state.asStateFlow()


    fun emailChange(value: String){
        _state.update {
            it.copy(email = value)
        }
    }
    fun usernameChange(value:String){
       _state.update {
           it.copy(username = value)
       }
        }

    fun passwordChange(value: String){
        _state.update {
            it.copy(password = value)
        }
    }
    fun confirmPasswordChange(value:String){
        _state.update {
            it.copy(
                confirmPassword = value,
            )
        }
    }
    fun viewPassword (){
        _state.update {
            it.copy(
                viewPassword = !_state.value.viewPassword
            )
        }
    }
     fun viewConfirmPassword(){
         _state.update {
             it.copy(
                 viewConfirmPassword = !_state.value.viewConfirmPassword
             )
         }
     }





}