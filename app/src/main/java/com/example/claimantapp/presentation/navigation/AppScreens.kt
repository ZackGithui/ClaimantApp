package com.example.claimantapp.presentation.navigation

sealed class AppScreens(val route:String) {
    data object SignUpScreen :AppScreens("sign_up_screen")

    data object SignInScreen: AppScreens("sign_in_screen")
    data object HomeScreen:AppScreens("home_screen")
    data object ForgotPasswordScreen:AppScreens("forgot_password")
    data object ProfileScreen:AppScreens("profile_screen")
}