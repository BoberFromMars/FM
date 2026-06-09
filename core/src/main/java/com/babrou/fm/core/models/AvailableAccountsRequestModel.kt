package com.babrou.fm.core.models

import com.google.gson.annotations.SerializedName

data class AvailableAccountsRequestModel (
    @SerializedName("UserId")
    val id: Int
)