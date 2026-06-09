package com.babrou.fm.feature.auth.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.babrou.fm.core.local.PreferencesManager
import com.babrou.fm.feature.home.navigation.HomeRoute
import com.babrou.fm.core.navigation.INavigationManager

@Composable
fun LoginScreen(
    navigationManager: INavigationManager,
) {
    LoginScreenInternal(
        navigationManager = navigationManager
    )
}

@Composable
internal fun LoginScreenInternal(
    navigationManager: INavigationManager,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(uiState) {
        uiState.shouldNavigateToSplash.let { shouldNavigateToSplash ->

            if (shouldNavigateToSplash) {
                uiState.destinationRoute?.let { destinationRoute ->
                    navigationManager.navigateToTop(HomeRoute)
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(
                value = login,
                onValueChange = {login = it},
                label = {Text(text = "Login")},
                modifier = Modifier.padding(bottom = 20.dp)
            )
            TextField(
                value = password,
                onValueChange = {password = it},
                label = {Text(text = "Password")},
                modifier = Modifier.padding(bottom = 20.dp),
                visualTransformation = PasswordVisualTransformation(),
            )
            Button(
                onClick = {
                    uiState.login = login
                    uiState.password = password
                    viewModel.login(login, password)
                }
            ) {
                Text(text = "Log into account")
            }
        }
    }
}