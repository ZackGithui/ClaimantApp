package com.example.claimantapp.presentation.signUp

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.claimantapp.R
import com.example.claimantapp.WindowType
import com.example.claimantapp.presentation.components.ClaimantButton
import com.example.claimantapp.presentation.components.ClaimantLabelButton
import com.example.claimantapp.presentation.components.ClaimantPasswordTextField
import com.example.claimantapp.presentation.components.ClaimantTextField
import com.example.claimantapp.presentation.navigation.AppScreens
import com.example.claimantapp.rememberWindowSize
import com.example.claimantapp.ui.theme.ClaimantAppTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Composable
fun SignUpScreen(
 signUpViewModel: SignUpViewModel= viewModel(),
                 navController: NavHostController) {
  val state= signUpViewModel.signUpState.collectAsState().value

  SignUpScreenContent(
    state = state,
    onEvent = {event ->

      when(event){
        is SignUpEvent.OnUsernameChange ->{
          signUpViewModel.usernameChange(event.username)
        }

        is SignUpEvent.OnConfirmPassword -> {
          signUpViewModel.confirmPasswordChange(event.confirmPassword)

        }
        is SignUpEvent.OnEmailChange -> {
          signUpViewModel.emailChange(event.email)
        }
        is SignUpEvent.OnPasswordChange -> {
          signUpViewModel.passwordChange(event.password)
        }
        SignUpEvent.OnPasswordEyeToggled -> {
          signUpViewModel.viewPassword()
        }
        SignUpEvent.OnSignUpButtonClicked -> {

        }

        SignUpEvent.OnConfirmPasswordEyeToggled -> {
          signUpViewModel.viewConfirmPassword()
        }

        SignUpEvent.OnSignInButtonClicked -> {
          navController.navigate(AppScreens.SignInScreen.route)
        }
      }


    }
    )



}


@Composable
fun SignUpScreenContent(
  modifier: Modifier = Modifier,
  state: SignUpStates,
  onEvent: (SignUpEvent) -> Unit
) {








//@Composable
//fun CompactScreen(modifier: Modifier = Modifier,
                  //state: SignUpStates,
                  //onEvent: (SignUpEvent) -> Unit) {
  var showErrors by remember { mutableStateOf(false) }


  Column(

    modifier = modifier
      // .padding(paddingValues)
      .background(MaterialTheme.colorScheme.background)
      .padding(top = 0.dp)
      //.padding(12.dp)
      .fillMaxSize()


    ,



    ) {
    Spacer(modifier = Modifier.height(10.dp))


    Column(modifier = Modifier
      .fillMaxWidth()
      .padding(top = 25.dp)
      // .fillMaxHeight(0.23f)
      ,
      horizontalAlignment = Alignment.CenterHorizontally,
    )
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
        text = stringResource(id = R.string.create_account),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onBackground,
        fontSize = 27.sp
      )

    }



    Spacer(modifier = Modifier.height(16.dp))

    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),

      ) {
      Column {

        var userNameError by remember {
          mutableStateOf(false)
        }
        Text(
          text = stringResource(id = R.string.username),
          style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
          color = MaterialTheme.colorScheme.onBackground
        )
        ClaimantTextField(
          value = state.username,
          onValueChange = {
            onEvent(SignUpEvent.OnUsernameChange(it))
           if(showErrors) userNameError= state.username.isBlank()



          },

          placeholder = stringResource(id = R.string.placeholder_username),
          isError=userNameError && showErrors


        )
        if (showErrors && userNameError){
          Text(text = "Enter a valid Username",
           //style = MaterialTheme.colorScheme.error
          )

        }





        Spacer(modifier = Modifier.height(10.dp))
        Text(
          text = stringResource(id = R.string.email),
          style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
          color = MaterialTheme.colorScheme.onBackground
        )
        var errorEmail by remember {
          mutableStateOf(false)
        }
        ClaimantTextField(
          value = state.email,
          onValueChange = { onEvent(SignUpEvent.OnEmailChange(it))
                          if (showErrors)errorEmail= state.email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(state.email).matches()},
          placeholder = stringResource(id = R.string.placeholder_email),
          isError = errorEmail && showErrors
        )
        if (errorEmail && showErrors){
          Text(text = "Enter a valid email")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
          text = stringResource(id = R.string.password),
          style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
          color = MaterialTheme.colorScheme.onBackground
        )
        var passwordError by remember {
          mutableStateOf(false)
        }
        val passwordRegex = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$".toRegex()
        ClaimantPasswordTextField(
          value = state.password,
          onValueChange = { onEvent(SignUpEvent.OnPasswordChange(it))
                          if(showErrors)passwordError= state.password.isBlank() || !state.password.matches(passwordRegex) },
          placeholder = stringResource(id = R.string.placeholder_password),
          onTrailingIconClicked = {
            onEvent(SignUpEvent.OnPasswordEyeToggled)

          },
          isPasswordVisible = state.viewPassword,
          isError = passwordError && showErrors
        )

        if (passwordError && showErrors){
          Text(text = "A password must contain a letter, a digit and a special character ie #")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
          text = stringResource(id = R.string.Confirm_password),
          style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
          color = MaterialTheme.colorScheme.onBackground
        )
        var confirmPasswordError by remember {
          mutableStateOf(false)
        }
        ClaimantPasswordTextField(
          value = state.confirmPassword,
          onValueChange = { onEvent(SignUpEvent.OnConfirmPassword(it))
                          if(showErrors)  confirmPasswordError= state.confirmPassword != state.password},
          placeholder = stringResource(id = R.string.placeholder_Confirmpassword),
          onTrailingIconClicked = {
            onEvent(SignUpEvent.OnConfirmPasswordEyeToggled)

          },
          isPasswordVisible = state.viewConfirmPassword,
          isError =confirmPasswordError && showErrors
        )
        if(confirmPasswordError && showErrors){
          Text(text = "password do not match")
        }
      }
      Spacer(modifier = Modifier.height(10.dp))
      val passwordRegex = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$".toRegex()
      ClaimantButton(
        title = stringResource(id = R.string.signUp),
        onClick = {  showErrors = true
         var  userNameError = state.username.isBlank()
          var errorEmail = state.email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(state.email).matches()
        var  passwordError = state.password.isBlank() || !state.password.matches(passwordRegex)
          var confirmPasswordError = state.confirmPassword != state.password
          if (!userNameError && !errorEmail && !passwordError && !confirmPasswordError) {
            // Handle successful sign-up
          }
        },
        modifier = Modifier.fillMaxWidth(),

        )
      Spacer(modifier = Modifier.height(8.dp))
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .height(70.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
      ) {
        ClaimantLabelButton(
          unClickableText = stringResource(id = R.string.already_have_an_account),

          //modifier = Modifier.padding(top = 13.dp),
          clickableText = stringResource(id = R.string.login),
          onClick = { onEvent(SignUpEvent.OnSignInButtonClicked) }
        )
      }

    }
    // }
  }
}
  //}
//}

/*@Composable
fun MediumtoExtended( modifier: Modifier = Modifier,
                      state: SignUpStates,
                      onEvent: (SignUpEvent) -> Unit) {
  Row (modifier = Modifier
    .fillMaxWidth()
    .background(MaterialTheme.colorScheme.background)
    .padding(12.dp),
    horizontalArrangement = Arrangement.SpaceAround){

      Spacer(modifier = Modifier.height(10.dp))


      Column(modifier = Modifier

        .padding(top = 25.dp)
        .fillMaxHeight(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,


      )
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
          text = stringResource(id = R.string.create_account),
          style = MaterialTheme.typography.titleLarge,
          color = MaterialTheme.colorScheme.onBackground,
          fontSize = 27.sp
        )

      }



      Spacer(modifier = Modifier.width(16.dp))


      Column(
        modifier = Modifier
        //  .padding(16.dp)
          .fillMaxHeight(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center



        ) {
        Column {
          Text(
            text = stringResource(id = R.string.username),
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
            color = MaterialTheme.colorScheme.onBackground
          )
          ClaimantTextField(
            value = state.username,
            onValueChange = {
              onEvent(SignUpEvent.OnUsernameChange(it))
            },
            placeholder = stringResource(id = R.string.placeholder_username),
          )

          Spacer(modifier = Modifier.height(10.dp))
          Text(
            text = stringResource(id = R.string.email),
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
            color = MaterialTheme.colorScheme.onBackground
          )
          ClaimantTextField(
            value = state.email,
            onValueChange = { onEvent(SignUpEvent.OnEmailChange(it)) },
            placeholder = stringResource(id = R.string.placeholder_email),
          )
          Spacer(modifier = Modifier.height(10.dp))
          Text(
            text = stringResource(id = R.string.password),
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
            color = MaterialTheme.colorScheme.onBackground
          )
          ClaimantPasswordTextField(
            value = state.password,
            onValueChange = { onEvent(SignUpEvent.OnPasswordChange(it)) },
            placeholder = stringResource(id = R.string.placeholder_password),
            onTrailingIconClicked = {
              onEvent(SignUpEvent.OnPasswordEyeToggled)
            },
            isPasswordVisible = state.viewPassword
          )
          Spacer(modifier = Modifier.height(10.dp))
          Text(
            text = stringResource(id = R.string.Confirm_password),
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
            color = MaterialTheme.colorScheme.onBackground
          )
          ClaimantPasswordTextField(
            value = state.confirmPassword,
            onValueChange = { onEvent(SignUpEvent.OnConfirmPassword(it)) },
            placeholder = stringResource(id = R.string.placeholder_Confirmpassword),
            onTrailingIconClicked = {
              onEvent(SignUpEvent.OnConfirmPasswordEyeToggled)
            },
            isPasswordVisible = state.viewConfirmPassword
          )
        }
        Spacer(modifier = Modifier.height(10.dp))

        ClaimantButton(
          title = stringResource(id = R.string.signUp),
          onClick = { onEvent(SignUpEvent.OnSignUpButtonClicked) },
          modifier = Modifier.fillMaxWidth(),

          )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
          horizontalArrangement = Arrangement.End,
          verticalAlignment = Alignment.CenterVertically
        ) {
          ClaimantLabelButton(
            unClickableText = stringResource(id = R.string.already_have_an_account),

            //modifier = Modifier.padding(top = 13.dp),
            clickableText = stringResource(id = R.string.login),
            onClick = { onEvent(SignUpEvent.OnSignInButtonClicked) }
          )
        }

      }
      // }



  }

}
*/




@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showSystemUi = true )
@Composable
private fun SignUpScreen2Preview() {
  ClaimantAppTheme {

    SignUpScreenContent(
      state = SignUpStates(),
      onEvent = {},
    )


  }

}
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true )
@Composable
private fun SignUpScreenPreview() {
  ClaimantAppTheme {

    SignUpScreenContent(
      state = SignUpStates(),
      onEvent = {},
    )


  }

}

