package com.example.claimantapp.presentation.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.claimantapp.R
import com.example.claimantapp.ui.theme.ClaimantAppTheme

@Composable
fun ClaimantTextField(
    modifier: Modifier = Modifier,
    isError:Boolean,


    value: String="",
    onValueChange: (String) ->Unit={},
    placeholder: String="",
    colors: TextFieldColors=OutlinedTextFieldDefaults.colors(
        focusedBorderColor = MaterialTheme.colorScheme.primary
    ),
                      ) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        singleLine = true,

        value = value,
        onValueChange = onValueChange,
        shape = MaterialTheme.shapes.large,
        textStyle = MaterialTheme.typography.bodyLarge,

        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodyLarge.copy(
                    MaterialTheme.colorScheme.onBackground.copy( alpha = 0.5f)
                ),
                color = MaterialTheme.colorScheme.onBackground,


            )

        },
        colors = colors
    )
}

@Composable
fun ClaimantPasswordTextField(
        modifier: Modifier = Modifier,
        value: String="",
        isError: Boolean,

        onValueChange: (String) -> Unit={},
        placeholder: String="",
        colors: TextFieldColors=OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary
        ),
        isPasswordVisible:Boolean=false,
        onTrailingIconClicked:()->Unit


    ) {
        OutlinedTextField(
            modifier=modifier.fillMaxWidth(),
            singleLine = true,

            value = value,
            onValueChange = onValueChange,
           shape = MaterialTheme.shapes.large,
            visualTransformation = if(isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            textStyle = MaterialTheme.typography.bodyLarge,
            placeholder = {
                    Text(text = placeholder,
                style = MaterialTheme.typography.bodyLarge.copy(
                    MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                ),
                        color = MaterialTheme.colorScheme.onBackground,
               )},
            trailingIcon = {
                IconButton(onClick = onTrailingIconClicked) {
                Icon(
                    imageVector = if (isPasswordVisible) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff,
                    contentDescription = if (isPasswordVisible) "Hide password" else "Show password"
                )
            }
            },
            colors=colors)


    }


@Preview
@Composable
private fun ClaimantTextFieldPrev() {
    ClaimantAppTheme {
        ClaimantTextField(isError=false)



    }

}