package com.babrou.fm.feature.auth.data.model

import com.google.gson.annotations.SerializedName

internal data class AuthRequestModel(
    @SerializedName("login")
    val login: String,
    @SerializedName("password")
    val password: String,
)