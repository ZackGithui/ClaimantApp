package com.example.claimantapp.presentation.homeScreen

import androidx.compose.material3.Text
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.claimantapp.data.ClaimantDataItem
import com.example.claimantapp.domain.Repository
import com.example.claimantapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) :ViewModel(){

        private val _state= MutableStateFlow(ClaimantState())
        var state:StateFlow<ClaimantState> = _state.asStateFlow()

        fun onEvents(events: ClaimantEvents){
                when(events){


                        is ClaimantEvents.onSearchQueryChanged ->{
                                _state.value=_state.value.copy(onSearchQuery =  events.query)

                                viewModelScope.launch {
                                        if (events.query.all{it.isLetter()|| it.isWhitespace()}){
                                                searchClaimant(query =_state.value.onSearchQuery)
                                        }else {
                                                searchById(id = _state.value.onSearchQuery)
                                        }

                                }





                        }


                }


        }




private fun searchClaimant(query:String){
if(query.isEmpty()){
        return
}
        viewModelScope.launch {
                repository.getClaimantByName(name=query).collect{result->
                        when(result){
                                is Resource.Success ->{
                                        _state.value=_state.value.copy(
                                                data= result.data?: emptyList(),
                                                isLoading = false,
                                                error = null

                                        )

                                }

                                is Resource.Error -> {
                                        _state.value=_state.value.copy(

                                                error = result.message?:"Unexpected error occurred",
                                                isLoading = false
                                        )
                                }
                                is Resource.Loading ->{
                                        _state.value=_state.value.copy(
                                                isLoading = true
                                        )
                                }
                        }


                }
        }



}


    private fun searchById(id:String)  {
            if(id.isEmpty()){
                    return
            }
            viewModelScope.launch {
                    repository.getClaimantByIdNumber(idNumber = id).collect{result->
                            when(result){
                                    is Resource.Success ->{
                                            _state.value=_state.value.copy(
                                                    data= result.data?: emptyList(),
                                                    isLoading = false,
                                                    error = null

                                            )

                                    }

                                    is Resource.Error -> {
                                            _state.value=_state.value.copy(

                                                    error = result.message?:"Unexpected error occurred",
                                                    isLoading = false
                                            )
                                    }
                                    is Resource.Loading ->{
                                            _state.value=_state.value.copy(
                                                    isLoading = true
                                            )
                                    }
                            }


                    }
            }


    }


}


data class ClaimantState(
        var data:List<ClaimantDataItem> = emptyList(),
        var isLoading:Boolean=false,
        var error:String? = null,
        var onSearchQuery:String="",
        var idQuery:String=""

        )

sealed class ClaimantEvents{
       data class onSearchQueryChanged(var query:String): ClaimantEvents()

}