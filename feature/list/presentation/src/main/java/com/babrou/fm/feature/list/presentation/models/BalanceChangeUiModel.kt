package com.babrou.fm.feature.list.presentation.models

import com.babrou.fm.feature.list.domain.model.BalanceChangeModel
import com.babrou.fm.feature.list.domain.model.BalanceTypeModel
import java.time.LocalDateTime

data class BalanceChangeUiModel (
    //BalanceChange fields
    val id: Int,
    val accountId: Int,
    val changeTypeId: Int,
    val money: Double,
    val date: LocalDateTime,
    val comment: String?,

    //BalanceTypes fields
    val typeName: String,
    val isIncrement: Boolean
) {
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
}