package com.babrou.fm.feature.list.presentation.models

import com.babrou.fm.core.theme.component.table.ITablePreviewModel
import com.babrou.fm.feature.list.domain.model.BalanceChangeModel
import com.babrou.fm.feature.list.domain.model.BalanceTypeModel

data class BalanceChangeUiModel(
    //BalanceChange fields
    val id: Int,
    val accountId: Int,
    val changeTypeId: Int,
    val money: Double,
    val date: String,
    val comment: String?,

    //BalanceTypes fields
    val typeName: String,
    val isIncrement: Boolean
): ITablePreviewModel {
    fun getBalanceChange(): BalanceChangeModel {
        return BalanceChangeModel(
            id = this.id,
            accountId = this.accountId,
            changeTypeId = this.changeTypeId,
            money = this.money,
            date = this.date,
            comment = this.comment
        )
    }

    fun getBalanceType(): BalanceTypeModel {
        return BalanceTypeModel (
            id = this.changeTypeId,
            name = this.typeName,
            isIncrement = this.isIncrement
        )
    }

    fun getBalanceTableModel() : BalanceChangeTableViewModel {
        val moneyWithType: Double = if (isIncrement) money else money * -1
        return BalanceChangeTableViewModel(
            id = this.id,
            date = this.date,
            money = this.money,
            typeName = this.typeName,
            comment = this.comment,
        )
    }

    override fun getItemId(): Int {
        return id
    }

}