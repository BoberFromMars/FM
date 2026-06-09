package com.babrou.fm.feature.auth.domain

import com.babrou.fm.core.api.Result
import com.babrou.fm.core.api.ITokenRefresher
import com.babrou.fm.feature.auth.domain.model.AuthModel

interface IAuthRepository : ITokenRefresher {
    fun hasUser(): Boolean
    suspend fun login(login: String, password: String): Result<AuthModel>
}
