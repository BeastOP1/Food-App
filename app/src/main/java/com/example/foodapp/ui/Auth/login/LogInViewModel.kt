package com.example.foodapp.ui.Auth.login

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.data.FoodApi
import com.example.foodapp.data.auth.GoogleAuthProvider
import com.example.foodapp.data.models.LogInRequest
import com.example.foodapp.data.models.OAuthRequest
import com.example.foodapp.ui.Auth.signup.SIgnUpViewModel
import com.example.foodapp.ui.Auth.signup.SIgnUpViewModel.SignUpNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val foodApi: FoodApi
) : ViewModel() {

    val googleAuthProvider = GoogleAuthProvider()

    private val _uiState = MutableStateFlow<LogInStatus>(LogInStatus.Nothing)
    val uiState = _uiState.asStateFlow()


    private val _navigationEvent = MutableSharedFlow<LogInNavigation>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()


    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    fun onPasswordChange(password: String) {
        _password.value = password
    }


    fun onEmailChange(email: String) {
        _email.value = email
    }


    fun onLogInClick() {

        viewModelScope.launch() {
            try {
                _uiState.value = LogInStatus.Loading
                val response = foodApi.login(
                    LogInRequest(
                        email = _email.value,
                        password = _password.value
                    )
                )
                if (response.token.isNotEmpty()) {
                    _uiState.value = LogInStatus.Success

                    _navigationEvent.emit(LogInNavigation.NavigateToHome)

                }

            } catch (e: Exception) {
                _uiState.value = LogInStatus.Failure
                e.printStackTrace()
            }

        }
    }


    sealed class LogInNavigation {
        object NavigateToHome : LogInNavigation()
        object NavigateToSignUp : LogInNavigation()
    }

    sealed class LogInStatus {
        object Nothing : LogInStatus()
        object Success : LogInStatus()
        object Failure : LogInStatus()
        object Loading : LogInStatus()
    }


    fun onGoogleSignInClicked(context: Context) {
        viewModelScope.launch {
            _uiState.value = LogInStatus.Loading
            val response = googleAuthProvider.signUp(
                context,
                CredentialManager.create(context)
            )
            if (response != null) {
                //here we have to send Request to our back-end code it will give us the credential we can use it 

                val request = OAuthRequest(
                    token = response.token,
                    provider = "google"
                )
                val res = foodApi.oAuth(request)

                if (res.token.isNotEmpty()) {
                    _uiState.value = LogInStatus.Success
                    _navigationEvent.emit(LogInNavigation.NavigateToHome)

                } else {
                    _uiState.value = LogInStatus.Failure

                }
            } else {
                _uiState.value = LogInStatus.Failure
            }

        }
    }

    fun onFaceBookSigInClicked() {

    }
}