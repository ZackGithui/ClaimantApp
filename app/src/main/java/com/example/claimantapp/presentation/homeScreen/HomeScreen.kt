package com.example.claimantapp.presentation.homeScreen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.claimantapp.R
import com.example.claimantapp.presentation.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Column (modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        


        ){
        Spacer(modifier = Modifier.height(10.dp)
            )
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End) {
            IconButton(onClick = { navController.navigate(AppScreens.ProfileScreen.route) },
                modifier = Modifier.size(100.dp)) {
                Image(painter = painterResource(id = R.drawable.user),
                    contentDescription = "",
                    modifier = Modifier.size(80.dp)
                        .clip(CircleShape)
                )

            }


        }
        Text(text = stringResource(id = R.string.search),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 27.sp
        )
        Spacer(modifier = Modifier.height(10.dp))


            var text by remember {
                mutableStateOf("")
            }
            var active by remember{
                mutableStateOf(false)
            }
            SearchBar(
                modifier = Modifier.fillMaxWidth(),

                query = text,
                onQueryChange = {text=it},
                onSearch ={active=false} ,
                active =active ,
                onActiveChange ={active=it},
                placeholder = { Text(text = "Search..", fontSize = 20.sp)},
                leadingIcon = {Icon(imageVector = Icons.Default.Search, contentDescription = "search")},
                trailingIcon = {if (active){

                    Icon(modifier = Modifier.clickable {
                        if (text.isNotEmpty()) {
                            text = ""
                        }else{
                            active=false
                        }

                    },
                        imageVector = Icons.Default.Close, contentDescription = "close")

                } }
                ) {

            }





        }








    }




@Preview( showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenPrev() {
    val context= LocalContext.current
    HomeScreen(navController = NavController(context))

}
@Preview( showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun HomeScreenPrev1() {
    val context= LocalContext.current
    HomeScreen(navController = NavController(context))

}