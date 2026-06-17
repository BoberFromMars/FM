package com.babrou.fm.core.theme.component.table

import androidx.compose.ui.graphics.Color
import android.text.Layout
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.babrou.fm.core.theme.lightColor
import com.babrou.fm.core.theme.lightGray
import com.babrou.fm.core.util.extractMembers
import androidx.compose.ui.text.TextStyle

@Composable
inline fun <reified T : ITablePreviewModel> ScrollableTable(
    data: List<T>,
    enableTableHeaderTitles: Boolean = true,
    headerTableTitles: List<TableHeaderItem>,
    headerTitlesBorderColor: Color = lightGray(),
    headerTitlesTextStyle: TextStyle = MaterialTheme.typography.bodySmall,
    headerTitlesBackGroundColor: Color = lightColor(),
    tableRowColors: List<Color> = listOf(
        lightColor(),
        lightColor(),
        ),
    rowBorderColor: Color = lightGray(),
    rowTextStyle: TextStyle = MaterialTheme.typography.bodySmall,
    tableElevation: Dp = 0.dp,
    shape: RoundedCornerShape = RoundedCornerShape(4.dp),
    borderStroke: BorderStroke = BorderStroke(
        width = 1.dp,
        color = lightGray(),
        ),
    disableVerticalDividers: Boolean = false,
    dividerThickness: Dp = 1.dp,
    horizontalDividerColor: Color = lightGray(),
    contentAlignment: Alignment = Alignment.Center,
    textAlign: TextAlign = TextAlign.Center,
    tablePadding: Dp = 0.dp,
    columnToIndexIncreaseWidth: Int? = null,
    columnForIndexToHide: Int? = null,
    crossinline onDeleteDelegateTable: (Int) -> Unit,
    crossinline onEditDelegateTable: (Int) -> Unit,
) {
    OutlinedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = tableElevation),
        shape = shape,
        border = borderStroke,
    ) {
        Column {
            if (enableTableHeaderTitles) {

                    TableHeader(
                        headerTableTitles = headerTableTitles,
                        headerTitlesTextStyle = headerTitlesTextStyle,
                        headerTitlesBackGroundColor = Color.Gray,
                        dividerThickness = dividerThickness,
                        contentAlignment = contentAlignment,
                        textAlign = textAlign,
                        tablePadding = tablePadding,
                        columnToIndexIncreaseWidth = columnToIndexIncreaseWidth,
                        columnForIndexToHide = columnForIndexToHide,
                    )

            }

            data.forEachIndexed { index, data ->
                val rowData = extractMembers(data).map {
                        it.second // getting the value from the returned Pair
                    }

                // alternate background colors between rows
                val tableRowBackgroundColor = if (index % 2 == 0) {
                    tableRowColors[0]
                } else {
                    tableRowColors[1]
                }


                TableContent(
                    data = rowData,
                    rowTextStyle = rowTextStyle,
                    rowBackGroundColor = tableRowBackgroundColor,
                    dividerThickness = dividerThickness,
//                      horizontalDividerColor = horizontalDividerColor,
                    contentAlignment = contentAlignment,
                    textAlign = textAlign,
                    rowBorderColor = Color.Black,
                    tablePadding = tablePadding,
                    columnForIndexToHide = columnForIndexToHide,
                    onDeleteDelegateTable = {onDeleteDelegateTable(data.getItemId())},
                    onEditDelegateTable =  {onEditDelegateTable(data.getItemId())},)
                }


        }
    }
}