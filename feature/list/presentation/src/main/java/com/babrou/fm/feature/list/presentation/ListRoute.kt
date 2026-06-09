package com.babrou.fm.feature.list.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.babrou.fm.feature.detail.navigation.DetailRoute
import com.babrou.fm.core.navigation.INavigationManager
import com.babrou.fm.core.util.ActionIntentEnum
import com.babrou.fm.feature.list.presentation.models.ListPopupMenu

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
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    fun onEdit(id: Int) {
        navigationManager.navigate(DetailRoute(id, ActionIntentEnum.EDIT))
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            horizontal = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(uiState.items) {
            Row() {
                Text(text = it.date.toString())
                Text(text = it.typeName)
                Text(text = if (it.isIncrement) it.money.toString() else "-${it.money}")
                Text(text = it.comment ?: "")
                ListPopupMenu(balanceChangeId = it.id,
                    onDeleteDelegate = {viewModel.deleteById(it.id)},
                    onEditDelegate = {onEdit(it.id)})
            }
        }

    }
    Button(
        onClick = {
            navigationManager.navigate(DetailRoute(0, ActionIntentEnum.NEW))
        }
    ) {
        Text(
            text = "Create New",
        )
    }

    LaunchedEffect(Unit) {
        viewModel.getItems()
    }
}