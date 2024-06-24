package com.example.claimantapp.presentation.signIn



sealed class SignInEvent {
    data class OnEmailChange(val email:String):SignInEvent()
    data class OnPasswordChange(val password:String):SignInEvent()
    data object OnSignInButtonClicked:SignInEvent()
    data object OnSignUpLabelButtonClicked:SignInEvent()
    data object OnPasswordEyeToggled:SignInEvent()
    data object ForgotPasswordLabelClicked:SignInEvent()
}

