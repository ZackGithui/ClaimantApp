package com.example.claimantapp.presentation.signUp

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
class SignUpViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _state = MutableStateFlow(SignUpStates())
    val signUpState = _state.asStateFlow()

    private val _signUpFlow= MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signUpFlow:StateFlow<Resource<FirebaseUser>?> = _signUpFlow



    fun emailChange(value: String) {
        _state.update { it.copy(email = value) }
    }

    fun usernameChange(value: String) {
        _state.update { it.copy(username = value) }
    }

    fun passwordChange(value: String) {
        _state.update { it.copy(password = value) }
    }



    fun viewPassword() {
        _state.update { it.copy(viewPassword = !_state.value.viewPassword) }
    }

    fun viewConfirmPassword() {
        _state.update { it.copy(viewConfirmPassword = !_state.value.viewConfirmPassword) }
    }

    fun signUp(name:String,email:String,password:String)=viewModelScope.launch {
         fun isValidEmail(email: String): Boolean {
            val emailRegex = Regex("""[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}""")
            return emailRegex.matches(email)
        }

      if (_state.value.username.isBlank()){
          _state.update { it.copy(usernameErrorMessage = "Name cannot be blank*") }
      }

        else if (_state.value.email.isBlank() || !isValidEmail(_state.value.email)){
            _state.update { it.copy(emailErrorMessage = "Enter a valid email*") }

        }
        else if (_state.value.password.length < 6){
            _state.update { it.copy(passwordErrorMessage = "Password be of length 6*") }
        }
        else {
            _state.update { it.copy(usernameErrorMessage = null) }
          _state.update { it.copy(emailErrorMessage = null) }
          _state.update { it.copy(passwordErrorMessage = null) }

            val result = authRepository.registerUser(name, email, password)
            _signUpFlow.value = result
        }

    }




}
