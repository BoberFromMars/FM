package com.babrou.fm.core.models

import com.google.gson.annotations.SerializedName

data class BalanceChangeDboModel (
    @SerializedName("accountId")
    val accountId: Int,
    @SerializedName("changeTypeId")
    val changeTypeId: Int,
    @SerializedName("money")
    val money: Double,
    @SerializedName("date")
    val date: String,
    @SerializedName("comment")
    val comment: String?
)