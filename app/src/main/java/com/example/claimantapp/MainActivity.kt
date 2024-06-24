package com.example.claimantapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.claimantapp.presentation.forgotPassword.ForgotPassword
import com.example.claimantapp.presentation.navigation.AppScreens
import com.example.claimantapp.presentation.signIn.SignInScreen
import com.example.claimantapp.presentation.signUp.SignUpScreen

import com.example.claimantapp.ui.theme.ClaimantAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            ClaimantAppTheme {
                App()

            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.SignUpScreen.route){
        composable(AppScreens.SignUpScreen.route){
            SignUpScreen( navController = navController)
        }
        composable(AppScreens.SignInScreen.route){
            SignInScreen( navController = navController)
        }
        composable(AppScreens.ForgotPasswordScreen.route){
            ForgotPassword(navController = navController)

        }
        }
    }



