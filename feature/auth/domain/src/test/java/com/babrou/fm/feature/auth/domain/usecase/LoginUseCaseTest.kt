package com.babrou.fm.feature.auth.domain.usecase

import com.google.common.truth.Truth
import com.babrou.fm.core.api.Result
import com.babrou.fm.feature.auth.domain.IAuthRepository
import com.babrou.fm.feature.auth.domain.LoginUseCase
import com.babrou.fm.feature.auth.domain.model.AuthModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class LoginUseCaseTest {

    private lateinit var autRepository: IAuthRepository
    private lateinit var loginUseCase: LoginUseCase

    @Before
    fun setUp() {
        autRepository = mockk()
        loginUseCase = LoginUseCase(autRepository)
    }

    @Test
    fun `given Result-Success AuthRequestModel when LoginUseCase() return Result-Success`() {
        // Given
        val email = "email"
        val password = "password"
        val response = Result.Success(mockk<AuthModel>())

        coEvery { autRepository.login(email, password) } returns response

        // When
        val result = runBlocking { loginUseCase(email, password) }

        // Then
        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
    }

    @Test
    fun `given Result-Error AuthRequestModel when LoginUseCase() return Result-Error`() {
        // Given
        val response: Result<AuthModel> = Result.Error("Login Failed")

        coEvery { autRepository.login(any(), any()) } returns response

        // When
        val result = runBlocking { loginUseCase("email", "password") }

        // Then
        Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
    }
}