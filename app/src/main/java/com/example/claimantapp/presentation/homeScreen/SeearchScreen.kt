package com.example.claimantapp.presentation.homeScreen



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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.claimantapp.presentation.components.NoInternet
import com.example.claimantapp.presentation.components.NothingFound


@Composable
fun SearchScreen(
    viewModel: HomeViewModel = hiltViewModel(),

    onCloseButtonClicked:()->Unit,
    onSearchIconClicked:()->Unit) {
    val state by viewModel.state.collectAsState()



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        // horizontalAlignment = Alignment.CenterHorizontally
    ) {




        var text by remember {
            mutableStateOf("")
        }
        val focusRequester= remember {
            FocusRequester()
        }
        var keyboardController= LocalSoftwareKeyboardController.current
        var focusManager= LocalFocusManager.current


        Column (horizontalAlignment = Alignment.CenterHorizontally){
            OutlinedTextField(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .focusRequester(focusRequester)
                    .padding(horizontal = 8.dp)

                ,

                shape = RoundedCornerShape(10.dp),
                value =state.onSearchQuery,
                onValueChange ={query->


                    viewModel.onEvents(ClaimantEvents.onSearchQueryChanged(query))






                },
                placeholder = { Text(text = "Search by your name or ID")},
                singleLine = true,
                leadingIcon = {Icons.Default.Search
                    Icon(imageVector = Icons.Default.Search, contentDescription = "search")

                },
                trailingIcon = {
                    if (text.isNotEmpty()) {

                        IconButton(onClick = {
                            if (text.isNotEmpty())
                                text = ""
                            else onCloseButtonClicked()

                        }) {
                            Icons.Default.Close
                            Icon(imageVector = Icons.Default.Close, contentDescription = "close")

                        }
                    }

                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search

                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        viewModel.onEvents(ClaimantEvents.onSearchQueryChanged(query = state.onSearchQuery))
                        onSearchIconClicked()
                        keyboardController?.hide()
                        focusManager.clearFocus()

                    }
                ),


                )
            if (state.error?.isNotEmpty()==true){
                NoInternet()
            }
            else if (state.data.isEmpty() && state.onSearchQuery.isNotEmpty()){
                NothingFound()
            }
            else{

            LazyColumn {

                items(state.data) { data ->
                    ClaimantInfo(claimantDataItem = data)

                    Divider(modifier = Modifier.height(2.dp))
                }

            }}

            }

        }







    }



