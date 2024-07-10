import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.claimantapp.data.repository.AuthRepository
import com.example.claimantapp.presentation.forgotPassword.ForgotState
import javax.inject.Inject


class ForgotPasswordViewModel :ViewModel(){
    private val _state= mutableStateOf(ForgotState())
    val state : ForgotState by _state

    fun onEmailChange(value:String){
        _state.value= _state.value.copy(email = value)
    }
}