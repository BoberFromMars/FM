package com.babrou.fm.feature.list.presentation.models

import com.babrou.fm.core.theme.component.table.ITablePreviewModel

data class BalanceChangeTableViewModel(
    val id: Int,

    val date: String,
    val money: Double,

    val typeName: String,
    val comment: String?,
): ITablePreviewModel {

    override fun getItemId(): Int {
        return id
    }
}