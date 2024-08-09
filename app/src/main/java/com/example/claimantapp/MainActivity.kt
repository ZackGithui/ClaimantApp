package com.example.claimantapp

import android.app.ActionBar
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.claimantapp.presentation.forgotPassword.ForgotPassword
import com.example.claimantapp.presentation.homeScreen.HomeScreen
import com.example.claimantapp.presentation.navigation.AppScreens
import com.example.claimantapp.presentation.profile.ProfileScreen
import com.example.claimantapp.presentation.signIn.SignInScreen
import com.example.claimantapp.presentation.signUp.SignUpScreen

import com.example.claimantapp.ui.theme.ClaimantAppTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


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
    val context = LocalContext.current
    val onboardingStatusFlow = remember { readOnboardingStatus(context) }
    val onboardingCompleted by onboardingStatusFlow.collectAsState(initial = false)
    NavHost(navController = navController, startDestination = if(onboardingCompleted) AppScreens.HomeScreen.route else AppScreens.SignInScreen.route){
        composable(AppScreens.SignUpScreen.route){
            SignUpScreen( navController = navController)
        }
        composable(AppScreens.SignInScreen.route){
            SignInScreen( navController = navController)
        }
        composable(AppScreens.ForgotPasswordScreen.route){
            ForgotPassword( navController = navController)

        }
        composable(AppScreens.HomeScreen.route){
            HomeScreen(navController = navController)
        }
        composable(AppScreens.ProfileScreen.route){
            ProfileScreen(navController = navController)
        }
        
        }
    }



