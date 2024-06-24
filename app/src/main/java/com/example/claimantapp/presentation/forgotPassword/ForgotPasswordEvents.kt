package com.example.claimantapp.presentation.forgotPassword

sealed class ForgotPasswordEvents {
    data class OnEmailChange(var value:String):ForgotPasswordEvents()
    data object OnResetPassword:ForgotPasswordEvents()




}