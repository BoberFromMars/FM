package com.babrou.fm.core.models

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime


data class BalanceChangeResponseModel (
    @SerializedName("id")
    val id: Int,
    @SerializedName("accountId")
    val accountId: Int,
    @SerializedName("changeTypeId")
    val changeTypeId: Int,
    @SerializedName("money")
    val money: Double,
    @SerializedName("date")
    val date: LocalDateTime,
    @SerializedName("comment")
    val comment: String?
)