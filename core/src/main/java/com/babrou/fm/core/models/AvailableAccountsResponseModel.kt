package com.babrou.fm.core.models

import com.google.gson.annotations.SerializedName

data class AvailableAccountsResponseModel (
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("balance")
    val balance: Double?
)