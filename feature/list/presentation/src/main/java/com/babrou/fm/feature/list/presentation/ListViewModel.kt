package com.babrou.fm.feature.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.babrou.fm.core.api.onSuccess
import com.babrou.fm.core.local.IPreferencesManager
import com.babrou.fm.feature.list.domain.model.BalanceChangeModel
import com.babrou.fm.feature.list.domain.model.BalanceChangeUseCase
import com.babrou.fm.feature.list.domain.model.BalanceTypeModel
import com.babrou.fm.feature.list.domain.model.BalanceTypeUseCase
import com.babrou.fm.feature.list.presentation.di.ListUiState
import com.babrou.fm.feature.list.presentation.models.BalanceChangeUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ListViewModel @Inject constructor(
    private val balanceUseCase: BalanceChangeUseCase,
    private val typesUseCase: BalanceTypeUseCase,
    val preferencesManager: IPreferencesManager,
) : ViewModel() {

    private val _accountId = MutableStateFlow(0)
    val accountId = _accountId.asStateFlow()

    private val _uiState = MutableStateFlow(ListUiState())
    val uiState = _uiState.asStateFlow()

    fun getItems() {
        val accId = preferencesManager.getAccountId()
        val userId = preferencesManager.getUserId()
        if (accId != null && userId != null) {
            _accountId.value = accId
            viewModelScope.launch {
                var balanceChangesList: List<BalanceChangeModel> = emptyList()
                var balanceTypesList: List<BalanceTypeModel> = emptyList()
                _uiState.value = ListUiState(isLoading = true)
                balanceUseCase.invokeGetBalance(accId).onSuccess {
                    balanceChangesList = it
                }
                typesUseCase.invoke().onSuccess {
                    balanceTypesList = it
                }
                if (balanceChangesList.isNotEmpty() && balanceTypesList.isNotEmpty()) {
                    val typeMap = balanceTypesList.associateBy { it.id }
                    val result = balanceChangesList.map { a -> BalanceChangeUiModel(
                        id = a.id,
                        accountId = a.accountId,
                        changeTypeId = a.changeTypeId,
                        money = a.money,
                        date = a.date,
                        comment = a.comment,
                        typeName = typeMap.getValue(a.changeTypeId).name,
                        isIncrement = typeMap.getValue(a.changeTypeId).isIncrement,
                    ) }
                    _uiState.update { state -> state.copy(items = result) }
                }
//            balanceChanges.invoke()
//            _uiState.update { state ->
//                state.copy(
//                    items = List(5) {
//                        "Item $it"
//                    }
//                )
//            }
            }
        }
    }

    fun deleteById(balanceChangeId: Int) {
        viewModelScope.launch {
            _uiState.value = ListUiState(isLoading = true)
            balanceUseCase.invokeDeleteBalance(balanceChangeId)
            _uiState.value = ListUiState(isLoading = false)
            getItems()
        }
    }
}