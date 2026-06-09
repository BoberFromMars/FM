package com.babrou.fm.feature.auth.domain

import com.babrou.fm.core.api.Result
import com.babrou.fm.feature.auth.domain.model.AuthModel
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: IAuthRepository
) {
    suspend operator fun invoke(login: String, password: String): Result<AuthModel> {
        return authRepository.login(login, password)
    }

}
