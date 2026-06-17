package com.babrou.fm.feature.list.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babrou.fm.core.api.onSuccess
import com.babrou.fm.core.local.IPreferencesManager
import com.babrou.fm.core.theme.component.table.TableHeaderItem
import com.babrou.fm.feature.list.domain.model.BalanceChangeModel
import com.babrou.fm.feature.list.domain.BalanceChangeUseCase
import com.babrou.fm.feature.list.domain.model.BalanceTypeModel
import com.babrou.fm.feature.list.domain.BalanceTypeUseCase
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

    private val _tableHeaders = MutableStateFlow(listOf(
        TableHeaderItem("Id", sortDelegate = {sortById()}),
        TableHeaderItem("Date", sortDelegate = {sortByDate()}),
        TableHeaderItem("Money", sortDelegate = {sortByMoney()}),
        TableHeaderItem("Type", sortDelegate = {sortByTypeName()}),
        TableHeaderItem("Comment", sortDelegate = {sortByComment()}),
        TableHeaderItem("", sortDelegate = {})
    ))
    val tableHeaders = _tableHeaders.asStateFlow()

    private val _uiState = MutableStateFlow(ListUiState())
    val uiState = _uiState.asStateFlow()

    private var _idSortFlag: Boolean = true
    private var _moneySortFlag: Boolean = true
    private var _typeNameSortFlag: Boolean = true
    private var _commentSortFlag: Boolean = true
    private var _dateSortFlag: Boolean = true

    fun getItems() {
        val accId = preferencesManager.getAccountId()
        val userId = preferencesManager.getUserId()
        Log.v("GET ITEMS", "acc = ${accId}, $userId")
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
                        date = a.date.substring(0, 10),
                        comment = a.comment,
                        typeName = typeMap.getValue(a.changeTypeId).name,
                        isIncrement = typeMap.getValue(a.changeTypeId).isIncrement,
                    ) }
                    val displayedList = result.map { a -> a.getBalanceTableModel()}
                    _uiState.update { state -> state.copy(items = result, displayedItems = displayedList, isLoading = false, hasData = true) }
                }
            }
        }
    }

    fun deleteById(balanceChangeId: Int) {
        viewModelScope.launch {
//            _uiState.value = ListUiState(isLoading = true)
            balanceUseCase.invokeDeleteBalance(balanceChangeId)
//            _uiState.value = ListUiState(isLoading = false)
            getItems()
        }
    }

    fun clearUi() {
        viewModelScope.launch {
            _uiState.update {
                state -> state.copy(
                    hasData = false,
                    isLoading = false
                )
            }
        }
    }

    private fun sortById() {
        viewModelScope.launch {
            if(_idSortFlag) {
                val sortedList = _uiState.value.displayedItems.sortedBy { it.id}
                _uiState.update {
                        state -> state.copy(items = state.items,
                    displayedItems = sortedList,
                    hasData = state.hasData,
                    isLoading = state.isLoading
                ) }
            }
            else {
                val sortedList = _uiState.value.displayedItems.sortedByDescending { it.id}
                _uiState.update {
                        state -> state.copy(items = state.items,
                    displayedItems = sortedList,
                    hasData = state.hasData,
                    isLoading = state.isLoading
                ) }
            }
            _idSortFlag = !_idSortFlag
        }
    }

    private fun sortByMoney() {
        viewModelScope.launch {
            if(_moneySortFlag) {
                val sortedList = _uiState.value.displayedItems.sortedBy { it.money}
                _uiState.update {
                        state -> state.copy(items = state.items,
                    displayedItems = sortedList,
                    hasData = state.hasData,
                    isLoading = state.isLoading
                ) }
            }
            else {
                val sortedList = _uiState.value.displayedItems.sortedByDescending { it.money}
                _uiState.update {
                        state -> state.copy(items = state.items,
                    displayedItems = sortedList,
                    hasData = state.hasData,
                    isLoading = state.isLoading
                ) }
            }
            _moneySortFlag = !_moneySortFlag
        }
    }

    private fun sortByTypeName() {
        viewModelScope.launch {
            if(_typeNameSortFlag) {
                val sortedList = _uiState.value.displayedItems.sortedBy { it.typeName}
                _uiState.update {
                        state -> state.copy(items = state.items,
                    displayedItems = sortedList,
                    hasData = state.hasData,
                    isLoading = state.isLoading
                ) }
            }
            else {
                val sortedList = _uiState.value.displayedItems.sortedByDescending { it.typeName}
                _uiState.update {
                        state -> state.copy(items = state.items,
                    displayedItems = sortedList,
                    hasData = state.hasData,
                    isLoading = state.isLoading
                ) }
            }
            _typeNameSortFlag = !_typeNameSortFlag
        }
    }

    private fun sortByComment() {
        viewModelScope.launch {
            if(_commentSortFlag) {
                val sortedList = _uiState.value.displayedItems.sortedBy { it.comment}
                _uiState.update {
                        state -> state.copy(items = state.items,
                    displayedItems = sortedList,
                    hasData = state.hasData,
                    isLoading = state.isLoading
                ) }
            }
            else {
                val sortedList = _uiState.value.displayedItems.sortedByDescending { it.comment}
                _uiState.update {
                        state -> state.copy(items = state.items,
                    displayedItems = sortedList,
                    hasData = state.hasData,
                    isLoading = state.isLoading
                ) }
            }
            _commentSortFlag = !_commentSortFlag
        }
    }

    private fun sortByDate() {
        viewModelScope.launch {
            if(_dateSortFlag) {
                val sortedList = _uiState.value.displayedItems.sortedBy { it.date}
                _uiState.update {
                        state -> state.copy(items = state.items,
                    displayedItems = sortedList,
                    hasData = state.hasData,
                    isLoading = state.isLoading
                ) }
            }
            else {
                val sortedList = _uiState.value.displayedItems.sortedByDescending { it.date}
                _uiState.update {
                        state -> state.copy(items = state.items,
                    displayedItems = sortedList,
                    hasData = state.hasData,
                    isLoading = state.isLoading
                ) }
            }
            _dateSortFlag = !_dateSortFlag
        }
    }
}