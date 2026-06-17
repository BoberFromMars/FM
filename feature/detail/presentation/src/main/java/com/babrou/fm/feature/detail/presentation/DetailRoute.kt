package com.babrou.fm.feature.detail.presentation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.setSelectedDate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.babrou.fm.core.R
import com.babrou.fm.core.local.PreferencesManager
import com.babrou.fm.core.models.BalanceChangeDboModel
import com.babrou.fm.core.models.BalanceChangeResponseModel
import com.babrou.fm.core.navigation.INavigationManager
import com.babrou.fm.core.theme.component.CustomDropdownMenu
import com.babrou.fm.core.theme.component.DdItem
import com.babrou.fm.core.theme.component.DropdownViewModel
import com.babrou.fm.core.util.ActionIntentEnum
import kotlinx.coroutines.android.awaitFrame
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

@Composable
fun DetailScreen(
    navigationManager: INavigationManager,
    actionIntent: ActionIntentEnum,
    id: Int
) {
    DetailScreenInternal(
        navigationManager = navigationManager,
        id = id,
        actionIntent = actionIntent
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun DetailScreenInternal(
    navigationManager: INavigationManager,
    id: Int,
    actionIntent: ActionIntentEnum,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val balanceChangeTypeList by viewModel.balanceChangeTypeList.collectAsState()
    val initFlag by viewModel.initializationFlag.collectAsState()

    var selectedType by remember { mutableStateOf(DdItem()) }
    var money by remember { mutableDoubleStateOf(0.0) }
//    var money: Double by viewModel.money.collectAsState()
    var comment by remember { mutableStateOf("") }
//    var comment by viewModel.comment.collectAsState()

    fun convertMillisToDate(millis: Long): String {
        val formatter = SimpleDateFormat("MM-dd-yyyy", Locale.getDefault())
        return formatter.format(Date(millis))
    }
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    var selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    fun changeTypeDdDelegate(item: DdItem): DdItem {
        selectedType = item
        viewModel.setType(item)
        return item
    }

    LaunchedEffect(Unit) {
        viewModel.getBalanceTypes()
        if (actionIntent == ActionIntentEnum.EDIT) {
            viewModel.setEditData(id)
        }
    }

    LaunchedEffect(uiState.wasInitialized) {
        uiState.wasInitialized.let {
            if (uiState.wasInitialized) {
                if (actionIntent == ActionIntentEnum.EDIT) {
                    val balanceChange = viewModel.getEditData()
                    if (balanceChange != null) {
                        val selectForEdit = balanceChangeTypeList.find { type ->
                            type.id == balanceChange.changeTypeId
                        }
                        if (selectForEdit != null) {
                            selectedType = selectForEdit
                            changeTypeDdDelegate(selectedType)
                        }
                        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                        val dateFormatted = LocalDate.parse(balanceChange.date.substring(0,10), formatter)
                        datePickerState.setSelectedDate(dateFormatted)
                        selectedDate = balanceChange.date
                        money = balanceChange.money
                        comment = balanceChange.comment ?: ""
                    }
                }
            }
        }

    }

    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start
    ) {
        IconButton(
            onClick = {
                navigationManager.navigateBack()
            }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
            )
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
                horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
            ) {
                CustomDropdownMenu(
                    label = "Type",
                    helperLabel = "Select type",
                    ddList = balanceChangeTypeList,
                    onSelectDelegate = ::changeTypeDdDelegate,
                )
                TextField(
                    value = money.toString(),
                    onValueChange = { money = it.toDouble() },
                    label = { Text(text = "How much money") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.padding(bottom = 20.dp, top = 20.dp)
                )
                OutlinedTextField(
                    value = selectedDate,
                    onValueChange = { },
                    label = { Text(stringResource(id = R.string.date)) },
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { showDatePicker = !showDatePicker }) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "Select date"
                            )
                        }
                    },
                    modifier = Modifier

//                    .height(64.dp)
                        .padding(bottom = 20.dp),
                )

                if (showDatePicker) {
                    Popup(
                        onDismissRequest = { showDatePicker = false },
                        alignment = Alignment.TopStart
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .offset(y = 64.dp)
                                .shadow(elevation = 4.dp)
                                .background(MaterialTheme.colorScheme.surface)
                                .padding(16.dp)
                        ) {
                            DatePicker(
                                state = datePickerState,
                                showModeToggle = false
                            )
                        }
                    }
                }
                TextField(
                    value = comment,
                    onValueChange = { comment = it },
                    label = { Text(text = stringResource(id = R.string.comment)) },
                    modifier = Modifier.padding(bottom = 20.dp),
                )
                Button(
                    onClick = {
                        val result =
                            viewModel.confirm(id, money, selectedDate, comment, actionIntent)
                        Log.v("RESULT CONFIRM", result.toString())
                        navigationManager.navigateBack()

                    }
                ) {
                    Text(text = if (id != 0) stringResource(id = R.string.confirm_changes) else stringResource(id = R.string.add_new))
                }
            }
    }
}