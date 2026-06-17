package com.babrou.fm.core.theme.component.table

import android.text.Layout
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle

@Composable
fun TableHeader(
    headerTableTitles: List<TableHeaderItem>,
    headerTitlesTextStyle: TextStyle,
    headerTitlesBackGroundColor: Color,
    dividerThickness: Dp,
    contentAlignment: Alignment,
    textAlign: TextAlign,
    tablePadding: Dp,
    columnToIndexIncreaseWidth: Int?,
    columnForIndexToHide: Int?,
) {
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .background(headerTitlesBackGroundColor)
                .padding(horizontal = tablePadding),
        ) {
            headerTableTitles.forEachIndexed { index, title ->
                if (index != columnForIndexToHide) {
                    val weight = if (index == columnToIndexIncreaseWidth) 8f else 2f
                    Box(
                        modifier = Modifier
                            .weight(weight),
//                    contentAlignment = contentAlignment,
                    ) {
                        TextButton(
                            onClick = title.sortDelegate,
//                        style = headerTitlesTextStyle,
                            modifier = Modifier
                                .height(38.dp)
                                .wrapContentHeight(),
                        ) {
                            Text(
                                text = title.label,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = textAlign
                            )
                        }
                    }
                }

            }
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .height(dividerThickness)
                .background(headerTitlesBackGroundColor),
            thickness = DividerDefaults.Thickness,
            color = DividerDefaults.color
        )
    }
}