package com.babrou.fm.feature.detail.presentation

import android.app.Notification
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babrou.fm.core.api.onError
import com.babrou.fm.core.api.onSuccess
import com.babrou.fm.core.local.IPreferencesManager
import com.babrou.fm.core.local.PreferencesManager
import com.babrou.fm.core.models.BalanceChangeDboModel
import com.babrou.fm.core.models.BalanceChangeResponseModel
import com.babrou.fm.core.theme.component.DdItem
import com.babrou.fm.core.util.ActionIntentEnum
import com.babrou.fm.feature.detail.domain.DetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
internal class DetailViewModel @Inject constructor(
    private val details: DetailUseCase,
    val preferencesManager: IPreferencesManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState = _uiState.asStateFlow()

    private val _initFlag = MutableStateFlow(false)
    val initializationFlag = _initFlag.asStateFlow()


    private val _changeTypeList: MutableStateFlow<List<DdItem>> = MutableStateFlow(emptyList())
    val balanceChangeTypeList: StateFlow<List<DdItem>> = _changeTypeList.asStateFlow()

    private val _selectedType: MutableStateFlow<DdItem?> = MutableStateFlow(null)
    val selectedType: StateFlow<DdItem?> = _selectedType.asStateFlow()

    private val _editBalance: MutableStateFlow<BalanceChangeResponseModel?> = MutableStateFlow(null)
    val editBalance: StateFlow<BalanceChangeResponseModel?> = _editBalance.asStateFlow()

    fun setType(item: DdItem) {
        _selectedType.value = item
    }

    fun getBalanceTypes() {
        viewModelScope.launch {
            details.invokeGetBalanceTypes()
                .onSuccess {
                    val mappedData = it.map{a -> DdItem(id = a.id, label = a.name) }
                    _changeTypeList.value = mappedData
                    _uiState.value = DetailUiState(
                        wasInitialized = true,
                        isLoading = false
                    )
                }
                .onError { message, _ ->
                    _uiState.value = DetailUiState(
                        isLoading = false,
                        wasInitialized = false,
                        error = message
                    )
                }
        }

    }

    fun setEditData(id: Int) {
        viewModelScope.launch {
            details.invokeGetBalanceChangeById(id).onSuccess {
                Log.v("TEST GET", "ON SUCCESS")
                _editBalance.value = BalanceChangeResponseModel(
                    id = it.id,
                    accountId = it.accountId,
                    changeTypeId = it.changeTypeId,
                    money = it.money,
                    date = it.date,
                    comment = it.comment,
                )
                _uiState.value = DetailUiState(wasInitialized = true, isLoading = false)
                Log.v("TEST GET", _editBalance.value?.date ?: "NO DATA")
            }

        }
    }

    fun getEditData() : BalanceChangeResponseModel? {
        return _editBalance.value
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun confirm(id: Int, money: Double, date: String, comment: String, intent: ActionIntentEnum): Boolean {
        var result = false
        viewModelScope.launch {
            val accountId = preferencesManager.getAccountId() ?: 0
            val changeTypeId = selectedType.value?.id ?: 0

            val formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy")
            val parsedDate = LocalDate.parse(date, formatter).toString() + "T00:00:00"
            Log.v("parsed date",parsedDate)

            if (intent == ActionIntentEnum.NEW) {
                Log.v("INTENT","NEW")
                val newBalanceChange = BalanceChangeDboModel(
                    accountId = accountId,
                    changeTypeId = changeTypeId,
                    money = money,
                    date = parsedDate,
                    comment = comment
                )
                details.invokeInsertBalanceChange(newBalanceChange).onSuccess {
                    _initFlag.value = true
                    result = true
                }
            }
            else if (intent == ActionIntentEnum.EDIT) {
//                Log.v("INTENT",changeTypeId.toString())
                val editBalanceChange = BalanceChangeResponseModel(
                    id = id,
                    accountId = accountId,
                    changeTypeId = changeTypeId,
                    money = money,
                    date = parsedDate,
                    comment = comment,
                )
                details.invokeUpdateBalanceChange(editBalanceChange).onSuccess {
                    _initFlag.value = true
                    result = true
                }
            }
        }
        return result
    }
}