package com.example.claimantapp.presentation.signIn

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.claimantapp.R
import com.example.claimantapp.presentation.components.ClaimantButton
import com.example.claimantapp.presentation.components.ClaimantLabelButton
import com.example.claimantapp.presentation.components.ClaimantPasswordTextField
import com.example.claimantapp.presentation.components.ClaimantTextField
import com.example.claimantapp.presentation.navigation.AppScreens
import com.example.claimantapp.saveOnboardingStatus
import com.example.claimantapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun SignInScreen(
    navController:NavHostController,
    signInViewModel:SignInViewModel= hiltViewModel()
) {
    val context = LocalContext.current
    val signInState=signInViewModel.signInState.collectAsState().value

    val signIn=signInViewModel.loginFlow.collectAsState()
    signIn.value?.let {
        when(it){
            is Resource.Error -> {

            }
            is Resource.Loading -> {
                CircularProgressIndicator(
                )
            }
            is Resource.Success -> {
                LaunchedEffect(Unit) {

                    saveOnboardingStatus(context, true)
                    navController.navigate(AppScreens.HomeScreen.route){
                        popUpTo(AppScreens.SignInScreen.route){
                            inclusive=true
                        }
                    }

                }

            }
        }
    }


    SignInScreenContent(
        signInState = signInState,
        onEvent={event: SignInEvent ->
            when(event){
                is SignInEvent.OnEmailChange -> {
                    signInViewModel.onEmailChange(event.email)
                }

                is SignInEvent.OnPasswordChange ->{
                    signInViewModel.onPasswordChange(event.password)

                }
                SignInEvent.OnSignInButtonClicked -> {
                     signInViewModel.login(email = signInState.email, password = signInState.password)

                }
                SignInEvent.OnSignUpLabelButtonClicked -> {
                    navController.navigate(AppScreens.SignUpScreen.route)
                }

                SignInEvent.OnPasswordEyeToggled -> {
                  signInViewModel.viewPassword()
                }

                SignInEvent.ForgotPasswordLabelClicked -> {
                    navController.navigate(AppScreens.ForgotPasswordScreen.route)
                }
            }

        })



}

@Composable
fun SignInScreenContent(
    modifier: Modifier=Modifier,
    signInState: SignInState,
   onEvent:(SignInEvent) -> Unit,
) {
   // Scaffold(modifier=modifier.fillMaxSize()


       // ) {paddingValues ->
        Column (modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            // .padding(paddingValues)
            .padding(12.dp)){

            Spacer(modifier = Modifier.height(10.dp))
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp),
                //.fillMaxHeight(0.3f),
                horizontalAlignment = Alignment.CenterHorizontally)
            {


                Image(
                    modifier = Modifier
                        .height(140.dp)
                        .width(140.dp),
                    painter = painterResource(id = R.drawable.icon),
                    contentDescription = "Background Image",
                    alignment = Alignment.Center,
                    contentScale = ContentScale.FillBounds,


                    )
                Text(
                    text = stringResource(id = R.string.SignIn),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 27.sp
                )

            }
            Spacer(modifier = Modifier.height(16.dp))




                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = stringResource(id = R.string.email),
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                    color = MaterialTheme.colorScheme.onBackground
                )
                ClaimantTextField(
                    value = signInState.email,
                    onValueChange = { onEvent(SignInEvent.OnEmailChange(it)) },
                    placeholder = stringResource(id = R.string.placeholder_email),

                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(id = R.string.password),
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                    color = MaterialTheme.colorScheme.onBackground
                )
                ClaimantPasswordTextField(
                    value = signInState.password,
                    onValueChange = { onEvent(SignInEvent.OnPasswordChange(it)) },
                    onTrailingIconClicked = {
                        onEvent(SignInEvent.OnPasswordEyeToggled)
                    },
                    placeholder = stringResource(id = R.string.password),
                    isPasswordVisible = signInState.viewPassword,

                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    ClaimantLabelButton(

                        unClickableText = "",
                        clickableText = stringResource(id = R.string.forgotPassword),
                        onClick = { onEvent(SignInEvent.ForgotPasswordLabelClicked) }
                    )

                }
                Spacer(modifier = Modifier.height(10.dp))


                ClaimantButton(title = stringResource(id = R.string.SignIn),
                    onClick = { onEvent(SignInEvent.OnSignInButtonClicked)

                    
                    })
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically


                ) {
                    ClaimantLabelButton(
                        unClickableText = stringResource(id = R.string.NoAccount),
                        modifier = Modifier.padding(12.dp),
                        clickableText = stringResource(id = R.string.signUp),
                        onClick = { onEvent(SignInEvent.OnSignUpLabelButtonClicked) }
                    )

                }





        }


   // }


}

@Preview(showSystemUi =true )
@Composable
private fun SignInScreenPreview() {
    SignInScreenContent(signInState = SignInState(), onEvent = {})
        

    
}
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun SignInScreenPreview2() {
    SignInScreenContent(signInState = SignInState(), onEvent = {})



}