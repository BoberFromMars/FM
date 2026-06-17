package com.babrou.fm.core.theme.component.table

data class TableHeaderItem (
    val label: String,
    val sortDelegate: () -> Unit
)