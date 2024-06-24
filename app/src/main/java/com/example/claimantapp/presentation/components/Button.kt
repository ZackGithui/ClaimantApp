package com.example.claimantapp.presentation.components

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.w3c.dom.Text

@Composable
fun ClaimantButton(

    modifier: Modifier = Modifier,
    title: String="",
    contentPaddingValues: PaddingValues=
    PaddingValues(
        start = 24.dp,
        top = 12.dp,
        bottom = 12.dp,
        end = 24.dp
    ),
    onClick:()->Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        contentPadding = contentPaddingValues,


        onClick = onClick) {
        Text(text = title,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 20.sp
            ))

    }
}

    @Composable
    fun ClaimantLabelButton(modifier: Modifier = Modifier,
                            unClickableText: String="",
                            clickableText: String="",

                            onClick: () -> Unit) {
        Row (verticalAlignment = Alignment.CenterVertically,


            modifier = modifier,
            ){
            Text(text = unClickableText,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                color = MaterialTheme.colorScheme.onBackground,)

        }
        TextButton(onClick = onClick) {
            Text(text = clickableText,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                color = MaterialTheme.colorScheme.primary,)

        }

    }


