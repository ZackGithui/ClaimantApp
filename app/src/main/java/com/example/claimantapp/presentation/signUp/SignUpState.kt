package com.example.claimantapp.presentation.signUp


   data class SignUpStates(
    var username:String ="",
    val email:String="",
    val password:String="",

    val viewPassword:Boolean=false,
    val viewConfirmPassword:Boolean=false,
    val isLoading: Boolean = false,
    val usernameErrorMessage: String? = null,
    val emailErrorMessage: String? = null,
    val passwordErrorMessage: String? = null




)


