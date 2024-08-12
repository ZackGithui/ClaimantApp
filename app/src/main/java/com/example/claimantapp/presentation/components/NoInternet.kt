package com.example.claimantapp.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.claimantapp.R

@Composable
fun NoInternet() {
    Column (modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        ){
        Image(
            painter = painterResource(id = R.drawable.internet ),
            contentDescription ="No internet connection" ,
            modifier = Modifier.size(300.dp))
        Spacer(modifier = Modifier.height(15.dp))
        Row(modifier = Modifier.padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.Center,

            ) {
            Text(

                text = stringResource(id = R.string.internet),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 19.sp, color = MaterialTheme.colorScheme.onBackground),


                )
        }





    }

}

@Preview(showBackground = true)
@Composable
 fun noInternetPrev() {
     NoInternet()

}