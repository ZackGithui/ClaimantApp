package com.example.claimantapp.presentation.forgotPassword

import ForgotPasswordViewModel
import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.claimantapp.R
import com.example.claimantapp.presentation.components.ClaimantButton
import com.example.claimantapp.presentation.components.ClaimantTextField
import com.example.claimantapp.presentation.navigation.AppScreens
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@Composable
fun ForgotPassword  ( navController: NavHostController, forgotPasswordViewModel:ForgotPasswordViewModel= viewModel()) {
    val context= LocalContext.current.applicationContext
    val states=forgotPasswordViewModel.state
ForgotPasswordContent(state = states,
    onEvent = {event :ForgotPasswordEvents->

        when(event){
            is ForgotPasswordEvents.OnEmailChange ->{
                forgotPasswordViewModel.onEmailChange(event.value)            }

            ForgotPasswordEvents.OnResetPassword -> {
               if(states.email.isEmpty()){
                   Toast.makeText(
                       context,
                       "Please enter an email!",
                       Toast.LENGTH_LONG
                   ).show()
               }
                else{
                    FirebaseAuth.getInstance().sendPasswordResetEmail(states.email)
                        .addOnCompleteListener {task->
                            if (task.isSuccessful){
                                Toast.makeText(
                                    context,
                                    "Email sent successfully to reset your password",
                                    Toast.LENGTH_LONG
                                ).show()

                                navController.navigate(AppScreens.SignInScreen.route)


                            }else{
                                Toast.makeText(
                                    context,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_LONG

                                ).show()
                            }

                        }
               }
            }
        }
    })



}

@Composable
fun ForgotPasswordContent(modifier: Modifier=Modifier,
                          state: ForgotState,
                          onEvent:(ForgotPasswordEvents)->Unit) {

    Column (modifier= modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(12.dp)){
        Spacer(modifier = Modifier.height(10.dp))
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp)
            ,
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
                text = stringResource(id = R.string.ResetPassword),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 27.sp
            )

        }


        Spacer(modifier = Modifier.height(80.dp))
        ClaimantTextField(
            value = state.email,
            onValueChange = {onEvent(ForgotPasswordEvents.OnEmailChange(it))},
            placeholder = stringResource(id = R.string.email),


        )
        Spacer(modifier = Modifier.height(20.dp))

        ClaimantButton(
            title = stringResource(id = R.string.ResetPassword),
            onClick = {onEvent(ForgotPasswordEvents.OnResetPassword)}
        )



    }



}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ResetPasswordPreview() {
    ForgotPasswordContent(state = ForgotState(), onEvent = {})



}
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ResetPasswordPreview2() {
    ForgotPasswordContent(state = ForgotState(), onEvent = {})



}