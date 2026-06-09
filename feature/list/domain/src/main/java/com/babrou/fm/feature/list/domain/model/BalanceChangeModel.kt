package com.babrou.fm.feature.list.domain.model

import java.time.LocalDateTime

data class BalanceChangeModel (
    val id: Int,
    val accountId: Int,
    val changeTypeId: Int,
    val money: Double,
    val date: LocalDateTime,
    val comment: String?
)