package com.babrou.fm.feature.detail.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.babrou.fm.core.navigation.INavigationManager
import com.babrou.fm.core.theme.component.CustomDropdownMenu
import com.babrou.fm.core.util.ActionIntentEnum

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


@Composable
internal fun DetailScreenInternal(
    navigationManager: INavigationManager,
    id: Int,
    actionIntent: ActionIntentEnum,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
//    val balanceChangeTypeList by viewModel.balanceChangeTypeList.collectAsState()
    var money by remember { mutableStateOf("") }
    var comment by remember { mutableStateOf("") }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row() {
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

            TextField(
                value = money,
                onValueChange = {money = it},
                label = {Text(text = "How much money")},
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.padding(bottom = 20.dp)
            )
            CustomDropdownMenu(
                label = "Type",
                helperLabel = "Select type",
//                ddList = balanceChangeTypeList
            )
            TextField(
                value = comment,
                onValueChange = {comment = it},
                label = {Text(text = "Comment")},
                modifier = Modifier.padding(bottom = 20.dp),
            )
        }

        Button(
            onClick = {
                viewModel.confirm()
            }
        ) {
            Text(text = "Log into account")
        }

    }

    LaunchedEffect(id) {
//        viewModel.setDetailId(id)
    }
}