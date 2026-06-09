package com.babrou.fm.core.base

import com.babrou.fm.core.api.Result
import com.babrou.fm.core.util.Constants
import retrofit2.Response

abstract class BaseRepository {
    
    protected suspend fun <T : Any> safeCall(call: suspend () -> Response<T>): Result<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful && response.body() != null) {
                Result.Success(response.body()!!)
            } else {
                Result.Error(
                    message = response.errorBody()?.string() ?: Constants.DEFAULT_ERROR
                )
            }
        } catch (e: Exception) {
            Result.Error(
                message = e.message ?: Constants.DEFAULT_ERROR,
                throwable = e
            )
        }
    }
}