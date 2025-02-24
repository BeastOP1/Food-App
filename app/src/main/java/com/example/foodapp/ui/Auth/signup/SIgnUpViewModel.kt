package com.example.foodapp.ui.Auth.signup

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.data.FoodApi
import com.example.foodapp.data.models.SignUpRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SIgnUpViewModel @Inject constructor(
    private val foodApi: FoodApi
) : ViewModel() {

    private val _uiState = MutableStateFlow<SignUpStatus>(SignUpStatus.Nothing)
    val uiState = _uiState.asStateFlow()


    private val _navigationEvent = MutableSharedFlow<SignUpNavigation>()

    val navigationEvent =_navigationEvent.asSharedFlow()


    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()


    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()


    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    fun onPasswordChange(password: String){
        _password.value = password
    }


    fun onEmailChange(email: String){
        _email.value = email
    }


    fun onNameChange(name: String){
        _name.value =  name
    }

     fun onSignUpClick(){

        viewModelScope.launch(){
            try {
                _uiState.value = SignUpStatus.Loading
                val response = foodApi.signUp(
                    SignUpRequest(
                        name  = _name.value,
                        email = _email.value,
                        password = _password.value
                    )
                )
                if(response.token.isNotEmpty()){
                    _uiState.value  = SignUpStatus.Success

                    _navigationEvent.emit(SignUpNavigation.NavigateToHome)

                }

            }catch (e: Exception){
                _uiState.value = SignUpStatus.Failure
                e.printStackTrace()
            }

        }
 }


    sealed class SignUpNavigation{
        object NavigateToHome: SignUpNavigation()
        object NavigateToLogIN: SignUpNavigation()
    }

    sealed class SignUpStatus{
        object Nothing: SignUpStatus()
        object Success: SignUpStatus()
        object Failure: SignUpStatus()
        object Loading: SignUpStatus()
    }
}