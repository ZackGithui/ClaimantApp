package com.example.claimantapp.presentation.signUp

import androidx.compose.material3.Text

fun signUpValidation(
    username:String,
    email:String,
    password:String,
    confirmPassword:String,
    setUsernameError:(String?)->Unit,
    setEmailError:(String?)->Unit,
    setPasswordError:(String?)->Unit,
    setConfirmPasswordError:(String?)->Unit
):Boolean {
    var isValid = true

    if (username.isBlank()) {
        setUsernameError("Username Cannot be empty")
        isValid = false

    }
    if (email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        setEmailError("Enter a valid email")
        isValid = false

    }
    val passwordRegex = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$".toRegex()
    if (!password.matches(passwordRegex) || password.isBlank()) {
        setPasswordError("A password must contain a letter, a digit and a special character ie #")
        isValid = false

    }
    if (confirmPassword != password) {
        setConfirmPasswordError("Passwords do not match ")
        isValid = false

    }
    return isValid

}
