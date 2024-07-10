package com.example.claimantapp.presentation.signIn

data class SignInState(
    var email :String="",
    var password: String="",
    var viewPassword:Boolean=false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
