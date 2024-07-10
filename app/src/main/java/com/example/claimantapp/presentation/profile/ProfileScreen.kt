package com.example.claimantapp.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.claimantapp.R
import com.example.claimantapp.presentation.components.ClaimantButton
import com.example.claimantapp.presentation.homeScreen.HomeScreen
import com.example.claimantapp.presentation.navigation.AppScreens
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel= hiltViewModel(),navController: NavHostController) {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(10.dp),
       // horizontalAlignment = Alignment.CenterHorizontally
        ) {

        Row(horizontalArrangement = Arrangement.Start) {
            IconButton(onClick = { navController.navigate(AppScreens.HomeScreen.route) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription ="Back",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .size(50.dp)
                        .background(MaterialTheme.colorScheme.primary))

            }

        }


        Spacer(modifier = Modifier.height(30.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.user),
                contentDescription = "user",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Row (modifier = Modifier.fillMaxWidth()
                 .padding(start = 30.dp),
                horizontalArrangement = Arrangement.Start){
                Text(text = "Name:",style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp, color = MaterialTheme.colorScheme.onBackground))
                Text(profileViewModel.currentUser?.displayName?:"", style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp, color = MaterialTheme.colorScheme.onBackground))

            }
            Spacer(modifier = Modifier.height(20.dp))
            Row (modifier = Modifier.fillMaxWidth()
                .padding(start = 30.dp),
                horizontalArrangement = Arrangement.Start){
                Text(text = "Email:",style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp, color = MaterialTheme.colorScheme.onBackground))
                Text(profileViewModel.currentUser?.email?:"", style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp, color = MaterialTheme.colorScheme.onBackground))

            }
            Spacer(modifier = Modifier.height(30.dp))



            ClaimantButton(title = stringResource(id = R.string.logout),
                onClick = {profileViewModel.logout()
                navController.navigate(AppScreens.SignInScreen.route){
                    popUpTo(AppScreens.HomeScreen.route){
                        inclusive=true
                    }
                }})

        }






    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ProfileScreen1() {
    val context= LocalContext.current
    ProfileScreen(navController =NavHostController(context))

}