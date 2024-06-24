package com.example.claimantapp.presentation.signUp

sealed class SignUpEvent {
    data object OnPasswordEyeToggled:SignUpEvent()
    data object OnConfirmPasswordEyeToggled:SignUpEvent()
    data class OnUsernameChange(val username:String):SignUpEvent()
    data class OnEmailChange(val email:String):SignUpEvent()
    data class OnPasswordChange(val password:String):SignUpEvent()
    data class OnConfirmPassword(val confirmPassword:String):SignUpEvent()
    data object OnSignUpButtonClicked: SignUpEvent()
    data object OnSignInButtonClicked:SignUpEvent()
}