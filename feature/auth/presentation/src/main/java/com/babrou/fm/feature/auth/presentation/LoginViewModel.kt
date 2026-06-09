package com.babrou.fm.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babrou.fm.core.api.onError
import com.babrou.fm.core.api.onSuccess
import com.babrou.fm.feature.home.navigation.HomeRoute
//import com.babrou.fm.core.local.IPreferencesManager
import com.babrou.fm.feature.auth.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val auth: LoginUseCase,
//    private val preferencesManager: IPreferencesManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

//    fun navigateToHome() {
//        viewModelScope.launch {
//
//        }
//    }

    fun login(login: String, password: String) {
        viewModelScope.launch {
            _uiState.value = LoginUiState(isLoading = true)

            auth.invoke(login, password)
                .onSuccess {
                    _uiState.value = LoginUiState(
                        shouldNavigateToSplash = true,
                        destinationRoute = HomeRoute,
                        isLoading = false
                    )
                }
                .onError { message, _ ->
                    _uiState.value = LoginUiState(
                        isLoading = false,
                        destinationRoute = null,
                        error = message
                    )
                }
        }
    }
}