package com.babrou.fm.core.theme.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuBoxScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> ExposedSearchMenu(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    items: List<T>,
    itemContent: @Composable (T) -> Unit,
    searchContent: @Composable () -> Unit,
    displayContent: @Composable ExposedDropdownMenuBoxScope.(Modifier) -> Unit,
    modifier: Modifier = Modifier,
    noItemsContent: @Composable () -> Unit = {
        Text("No items", modifier = Modifier.padding(8.dp).fillMaxWidth(), textAlign = TextAlign.Center)
    },
) {
    ExposedDropdownMenuBox(
        expanded = expanded, onExpandedChange = onExpandedChange,
        modifier = modifier
    ) {
        displayContent(Modifier)
        ExposedSearchableDropDownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChange.invoke(false) },
        ) {
            searchContent()
            if (items.isEmpty()) {
                noItemsContent()
            } else {
                items.forEach { item ->
                    itemContent(item)
                }
            }
        }
    }
}