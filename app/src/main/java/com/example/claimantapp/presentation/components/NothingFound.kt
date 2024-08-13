package com.example.claimantapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.claimantapp.R

@Composable
fun NothingFound() {
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        val composition by rememberLottieComposition(LottieCompositionSpec.Asset("nothing.json"))
        
        LottieAnimation(composition = composition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier
                .padding(16.dp)
                .size(300.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = stringResource(id = R.string.nothing), style = MaterialTheme.typography.bodyLarge.copy(fontSize = 22.sp, color = MaterialTheme.colorScheme.primary) )
        }

    }

