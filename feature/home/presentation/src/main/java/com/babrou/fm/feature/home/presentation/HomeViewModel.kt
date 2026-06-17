package com.babrou.fm.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.babrou.fm.core.api.onError
import com.babrou.fm.core.api.onSuccess
import com.babrou.fm.core.models.AvailableAccountsRequestModel
import com.babrou.fm.core.local.IPreferencesManager
import com.babrou.fm.core.theme.component.DdItem
import com.babrou.fm.feature.home.domain.HomeUseCase
import com.babrou.fm.feature.home.presentation.di.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val home: HomeUseCase,
    public val preferencesManager: IPreferencesManager,
//    private val selectedAccount: DdItem?,
): ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()
    private val _accList: MutableStateFlow<List<DdItem>> = MutableStateFlow(emptyList())
    val availableAccountsList: StateFlow<List<DdItem>> = _accList.asStateFlow()
    private val _selectedAccount: MutableStateFlow<DdItem?> = MutableStateFlow(null)
    val selectedAccount: StateFlow<DdItem?> = _selectedAccount.asStateFlow()
    fun getAvailableAccounts() {
        val id = preferencesManager.getUserId()
        if (id != null) {
            val model = AvailableAccountsRequestModel(id)
            viewModelScope.launch {
                _uiState.value = HomeUiState(isLoading = true)

                home.invoke(model)
                    .onSuccess {
                        val mappedData = it.map{a -> DdItem(id = a.id, label = a.name) }
                        _accList.value = mappedData
                        _uiState.value = HomeUiState(
//                        shouldNavigateToSplash = true,
//                        destinationRoute = HomeRoute,
//                            availableAccountsList = it,
                            shouldNavigateToList = false,
                            wasInitialized = true,
                            isLoading = false
                        )
                    }
                    .onError { message, _ ->
                        _uiState.value = HomeUiState(
                            isLoading = false,
                            shouldNavigateToList = false,
                            wasInitialized = false,
//                            availableAccountsList = emptyList(),
//                        destinationRoute = null,
                            error = message
                        )
                    }
            }
        }

    }

    fun setAccountId(accountId: Int) {
        viewModelScope.launch {
            preferencesManager.setAccountId(accountId)
        }
    }

    fun navigateToList () {
        viewModelScope.launch {
            _uiState.value = HomeUiState(
                wasInitialized = true,
                shouldNavigateToList = true,
                isLoading = false
            )
        }
    }

    fun updateState() {
        viewModelScope.launch {
            _uiState.value = HomeUiState(
                shouldNavigateToList = false,
                wasInitialized = true,
                isLoading = false
            )
        }
    }
}