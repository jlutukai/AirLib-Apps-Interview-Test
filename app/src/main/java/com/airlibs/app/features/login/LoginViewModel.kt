package com.airlibs.app.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.airlibs.app.R
import com.airlibs.app.features.login.models.LoginFormState
import com.airlibs.app.features.login.models.LoginUIState
import com.airlibs.data.sources.local.dataStore.AirLibsDataStore
import com.airlibs.domain.models.data.UserDetails
import com.airlibs.domain.models.data.utils.UiText
import com.airlibs.domain.useCases.SaveUserDetailsUseCase
import com.airlibs.domain.utils.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val saveUserDetailsUseCase: SaveUserDetailsUseCase,
) : ViewModel() {

    private val _loginFormState: MutableStateFlow<LoginFormState> =
        MutableStateFlow(LoginFormState())
    val loginFormState: StateFlow<LoginFormState> get() = _loginFormState.asStateFlow()

    private val _loginUiState: MutableStateFlow<LoginUIState> =
        MutableStateFlow(LoginUIState())
    val loginUiState: StateFlow<LoginUIState> get() = _loginUiState.asStateFlow()

    private val _events = Channel<LoginEvents>()
    val events = _events.receiveAsFlow()


    fun onEvent(event: LoginEvents) {
        when (event) {
            is LoginEvents.Error, LoginEvents.IdleState -> {
                viewModelScope.launch {
                    _events.send(event)
                }
            }

            LoginEvents.OnLogIn -> {
                if (isValidated()) {
                    onSaveUserDetails()
                }
            }
        }
    }

    private fun onSaveUserDetails() {
        viewModelScope.launch {
            val result = saveUserDetailsUseCase(
                UserDetails(
                    email = loginFormState.value.userName.inputValue.value
                )
            )
            when (result) {
                is ResultWrapper.GenericError -> {
                    _events.send(LoginEvents.Error(error = result.errorMessage))
                }

                ResultWrapper.Loading -> {

                }

                is ResultWrapper.Success -> {
                    _loginUiState.update {
                        it.copy(
                            userId = result.value
                        )
                    }
                }
            }
        }
    }

    private fun isValidated(): Boolean = with(loginFormState.value) {
        if (userName.inputValue.value.isEmpty()) {
            _loginFormState.update {
                it.copy(
                    userName = it.userName.copy(
                        validationMessage = R.string.required_field,
                        hasError = true
                    )
                )
            }
            return@with false
        }

        if (password.inputValue.value.isEmpty()) {
            _loginFormState.update {
                it.copy(
                    password = it.password.copy(
                        validationMessage = R.string.required_field,
                        hasError = true
                    )
                )
            }
            return@with false
        }

        return@with true
    }


}

sealed interface LoginEvents {
    data class Error(
        val error: UiText,
        val errorTitle: UiText = UiText.StringResource(R.string.error)
    ) : LoginEvents

    data object IdleState : LoginEvents
    data object OnLogIn : LoginEvents
}