package com.example.claimantapp.presentation.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.claimantapp.data.AuthRepository

import com.example.claimantapp.util.Resource
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val signInState = _state.asStateFlow()
    private val _loginFlow=MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow:StateFlow<Resource<FirebaseUser>?> = _loginFlow

    fun onEmailChange(value: String) {
        _state.update { it.copy(email = value) }
    }

    fun onPasswordChange(value: String) {
        _state.update { it.copy(password = value) }
    }

    fun viewPassword() {
        _state.update { it.copy(viewPassword = !_state.value.viewPassword) }
    }

    fun login(email:String,password:String)=viewModelScope.launch {
        val result=authRepository.loginUser(email, password)
        _loginFlow.value=result
    }


}
