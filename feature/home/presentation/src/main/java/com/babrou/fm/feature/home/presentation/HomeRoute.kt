package com.babrou.fm.feature.home.presentation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.babrou.fm.core.local.PreferencesManager
import com.babrou.fm.feature.list.navigation.ListRoute
import com.babrou.fm.core.navigation.INavigationManager
import com.babrou.fm.core.theme.component.CustomDropdownMenu
import kotlinx.coroutines.flow.asFlow

@Composable
fun HomeScreen(
    navigationManager: INavigationManager,
) {
    HomeScreenInternal(
        navigationManager = navigationManager
    )
}

@Composable
internal fun HomeScreenInternal(
    navigationManager: INavigationManager,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val accountsList by viewModel.availableAccountsList.collectAsState()
//    viewModel.getAvailableAccounts()
//    val accountsList = uiState.availableAccountsList.map { a -> if(a.name != null) a.name!! else "" }
    LaunchedEffect(uiState) {
        viewModel.getAvailableAccounts()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Home Screen")
            CustomDropdownMenu(
                label = "Choose account",
                helperLabel = "Family Accounts",
                ddList = accountsList,
            )
            Button(
                onClick = {
                    val selected = uiState.selectedAccount
                    if (selected!= null) {
                        viewModel.setAccountId(selected.id)
                    }
                    navigationManager.navigate(ListRoute)
                }
            ) {
                Text(text = "Select Account")
            }
        }
    }
}