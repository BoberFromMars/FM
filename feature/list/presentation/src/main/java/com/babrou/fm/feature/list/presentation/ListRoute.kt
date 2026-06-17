package com.babrou.fm.feature.list.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.babrou.fm.feature.detail.navigation.DetailRoute
import com.babrou.fm.core.navigation.INavigationManager
import com.babrou.fm.core.util.ActionIntentEnum
import com.babrou.fm.core.theme.component.table.ListPopupMenu
import com.babrou.fm.core.theme.component.table.ScrollableTable

@Composable
fun ListScreen(
    navigationManager: INavigationManager,
) {
    ListScreenInternal(
        navigationManager = navigationManager
    )
}

@Composable
internal fun ListScreenInternal(
    navigationManager: INavigationManager,
    viewModel: ListViewModel = hiltViewModel()
) {
    Log.v("TEST SCREEN INTERNAL FUNC","FUNC")

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val headers by viewModel.tableHeaders.collectAsState()

    fun onEdit(id: Int) {
        navigationManager.navigate(DetailRoute(id, ActionIntentEnum.EDIT))
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
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScrollableTable(
                data = uiState.displayedItems,
                tablePadding = 2.dp,
                headerTableTitles = headers,
                onEditDelegateTable = {id -> onEdit(id)},
                onDeleteDelegateTable = {id -> viewModel.deleteById(id)},
                columnForIndexToHide = 0
                )
            Button(
                onClick = {
                    navigationManager.navigate(DetailRoute(0, ActionIntentEnum.NEW))
                }
            ) {
                Text(
                    text = "Create New",
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        Log.v("LIST ROUTE", "NO DATA")
        viewModel.getItems()
    }
}