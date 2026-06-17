package com.babrou.fm.feature.home.presentation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.babrou.fm.contract.HomeRoute
import com.babrou.fm.core.R
import com.babrou.fm.core.local.PreferencesManager
import com.babrou.fm.feature.list.navigation.ListRoute
import com.babrou.fm.core.navigation.INavigationManager
import com.babrou.fm.core.theme.component.CustomDropdownMenu
import com.babrou.fm.core.theme.component.DdItem
import com.babrou.fm.feature.home.presentation.di.HomeUiState
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
    var selectedAccount by remember { mutableStateOf(DdItem()) }
//    viewModel.getAvailableAccounts()
//    val accountsList = uiState.availableAccountsList.map { a -> if(a.name != null) a.name!! else "" }

    fun accountDdDelegate(item: DdItem): DdItem {
        selectedAccount = item
        return item
    }

    LaunchedEffect(uiState) {
        if (!uiState.wasInitialized) {
            viewModel.getAvailableAccounts()
        }
        uiState.shouldNavigateToList.let { shouldNavigateToList ->
            if (uiState.shouldNavigateToList) {
                Log.v("Test", "SHOULD NAVIGATE")
                navigationManager.navigate(ListRoute)
                viewModel.updateState()
//                navigationManager.navigate(ListRoute)
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Home Screen",
                modifier = Modifier.padding(bottom = 20.dp)
            )
            CustomDropdownMenu(
                label = "Choose account",
                helperLabel = "Family Accounts",
                onSelectDelegate = ::accountDdDelegate,
                ddList = accountsList,
            )
            Button(
                modifier = Modifier.padding(top = 20.dp),
                onClick = {
                    if (selectedAccount.id != 0) {
                        viewModel.setAccountId(selectedAccount.id)
                        viewModel.navigateToList()
                    }
                }

            ) {
                Text(text = stringResource(id = R.string.select_account))
            }
        }
    }
}