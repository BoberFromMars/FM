package com.babrou.fm.feature.auth.data

import android.util.Log
import com.babrou.fm.core.api.Result
import com.babrou.fm.core.api.map
import com.babrou.fm.core.base.BaseRepository
import com.babrou.fm.core.local.IPreferencesManager
import com.babrou.fm.feature.auth.data.model.AuthRequestModel
import com.babrou.fm.feature.auth.data.remote.AuthService
import com.babrou.fm.feature.auth.domain.IAuthRepository
import com.babrou.fm.feature.auth.domain.model.AuthModel
import retrofit2.Response
import javax.inject.Inject

internal class AuthRepository @Inject constructor(
    private val authService: AuthService,
    private val prefs: IPreferencesManager
) : BaseRepository(), IAuthRepository {

    override fun hasUser(): Boolean {
        return prefs.hasUser()
    }

    override suspend fun login(login: String, password: String): Result<AuthModel> {
        val result = safeCall(
            call = {
                val requestModel = AuthRequestModel(
                    login = login,
                    password = password
                )
                authService.login(requestModel)
            }
        )
            .map {
            AuthModel(
                id = it
            )
        }
//        val test =safeCall(
//            call = {
//                val requestModel = AuthRequestModel(
//                    login = login,
//                    password = password
//                )
//                authService.login(requestModel)
//            }
//        )
//            Log.v("TEST LOGIN", test.toString())
        if (result is Result.Success) {
            val data = result.data.id
            prefs.setUserId(data)
        }
        return result
    }

    override suspend fun refreshToken(): Result<String> {
        return safeCall {
            //TODO: Not Implemented
            // authService.refreshToken()
            Response.success("")
        }
    }
}
