package com.babrou.fm.feature.splash.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babrou.fm.feature.home.navigation.HomeRoute
import com.babrou.fm.feature.auth.navigation.LoginRoute
import com.babrou.fm.core.local.IPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
internal class SplashViewModel @Inject constructor(
    private val preferencesManager: IPreferencesManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val hasUser = preferencesManager.hasUser()


//            val hasUser = IPreferencesManager.
//            val hasUser = true
            delay(1000.milliseconds)
            _uiState.value = SplashUiState(
//                destinationRoute = HomeRoute,
                destinationRoute = if (hasUser) HomeRoute else LoginRoute,
                isLoading = false
            )
        }
    }
}