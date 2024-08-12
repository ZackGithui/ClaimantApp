package com.example.claimantapp.presentation.homeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.claimantapp.data.ClaimantDataItem

@Composable
fun ClaimantInfo(claimantDataItem: ClaimantDataItem) {
    Card(modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )) {
        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Name: ",style=MaterialTheme.typography.bodyMedium.copy(fontSize =16.sp, color = MaterialTheme.colorScheme.onBackground))
                Text(text = claimantDataItem.name ?: "",modifier = Modifier.weight(1f),style=MaterialTheme.typography.bodyMedium.copy(fontSize =16.sp, color = MaterialTheme.colorScheme.onBackground))
                Spacer(modifier = Modifier.width(30.dp))
                Text(text = "ID: ",style=MaterialTheme.typography.bodyMedium.copy(fontSize =16.sp, color = MaterialTheme.colorScheme.onBackground))
                Text(text = claimantDataItem.iDNumber.toString() ?: "",modifier = Modifier.weight(1f),style=MaterialTheme.typography.bodyMedium.copy(fontSize =16.sp, color = MaterialTheme.colorScheme.onBackground))
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Amount: ",style=MaterialTheme.typography.bodyMedium.copy(fontSize =16.sp, color = MaterialTheme.colorScheme.onBackground))
                Text(text = claimantDataItem.amountDueToOwner.toString(),modifier = Modifier.weight(1f),style=MaterialTheme.typography.bodyMedium.copy(fontSize =16.sp, color = MaterialTheme.colorScheme.onBackground))
                Spacer(modifier = Modifier.width(30.dp))
                Text(text = "Holder: ",style=MaterialTheme.typography.bodyMedium.copy(fontSize =16.sp, color = MaterialTheme.colorScheme.onBackground))
                Text(text = claimantDataItem.holderName ?: "",modifier = Modifier.weight(1f),style=MaterialTheme.typography.bodyMedium.copy(fontSize =16.sp, color = MaterialTheme.colorScheme.onBackground))

            }
            Spacer(modifier = Modifier.height(10.dp))
            Row (modifier = Modifier.fillMaxWidth()){
                Text(text = "Postal Address: ", style=MaterialTheme.typography.bodyMedium.copy(fontSize =16.sp, color = MaterialTheme.colorScheme.onBackground))
                Text(text = claimantDataItem.ownersPostalAddress ?: "", modifier = Modifier.weight(1f),style=MaterialTheme.typography.bodyMedium.copy(fontSize =16.sp, color = MaterialTheme.colorScheme.onBackground))
            }


            
           
        }
      
    
    }


}