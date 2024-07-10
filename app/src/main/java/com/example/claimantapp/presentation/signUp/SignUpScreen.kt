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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.hilt.navigation.compose.hiltViewModel
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
import com.example.claimantapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Composable
fun SignUpScreen(
 signUpViewModel: SignUpViewModel= hiltViewModel(),
                 navController: NavHostController) {
  val state= signUpViewModel.signUpState.collectAsState().value

  val signUpFlow=signUpViewModel.signUpFlow.collectAsState()
  signUpFlow.value?.let {
    when(it){
      is Resource.Error -> {

      }
      is Resource.Loading -> {

        CircularProgressIndicator()

      }
      is Resource.Success -> {
        LaunchedEffect(Unit) {
          navController.navigate(AppScreens.HomeScreen.route){
            popUpTo(AppScreens.SignUpScreen.route){inclusive=true}
          }

        }

      }
    }
  }

  SignUpScreenContent(
    state = state,
    onEvent = {event ->

      when(event){
        is SignUpEvent.OnUsernameChange ->{
          signUpViewModel.usernameChange(event.username)
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
          signUpViewModel.signUp(state.username,state.email,state.password)

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



   Spacer(modifier = Modifier.height(7.dp))

    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),

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
          placeholder = stringResource(id = R.string.username),
        )
        Text(text = state.usernameErrorMessage?:"")



       // Spacer(modifier = Modifier.height(10.dp))
        Text(
          text = stringResource(id = R.string.email),
          style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
          color = MaterialTheme.colorScheme.onBackground
        )


        ClaimantTextField(
          value = state.email,
          onValueChange = { onEvent(SignUpEvent.OnEmailChange(it))
                        },
          placeholder = stringResource(id = R.string.email),

        )
        Text(text = state.emailErrorMessage?:"", color = Color.Red)
        //Spacer(modifier = Modifier.height(10.dp))
        Text(
          text = stringResource(id = R.string.password),
          style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
          color = MaterialTheme.colorScheme.onBackground
        )



        ClaimantPasswordTextField(
          value = state.password,
          onValueChange = { onEvent(SignUpEvent.OnPasswordChange(it))
                          },
          placeholder = stringResource(id = R.string.password),
          onTrailingIconClicked = {
            onEvent(SignUpEvent.OnPasswordEyeToggled)

          },
          isPasswordVisible = state.viewPassword,

        )
        Text(text = state.passwordErrorMessage?:"", color = Color.Red)


        //Spacer(modifier = Modifier.height(10.dp))

      }
      Spacer(modifier = Modifier.height(10.dp))

      ClaimantButton(
        title = stringResource(id = R.string.signUp),
        onClick = {
          onEvent(SignUpEvent.OnSignUpButtonClicked)
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



  }


}



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

