package com.babrou.fm.core.models

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class BalanceTypesResponseModel (
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("isIncrement")
    val isIncrement: Boolean,
)