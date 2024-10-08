package com.korea.common.utils

import retrofit2.Response

object ApiUtils {
    suspend fun <T, R> safeApiCall(
        apiCall: suspend () -> Response<T>,
        convert: (T) -> R,
    ): Result<R> {
        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(convert(it))
                } ?: Result.failure(Exception("Response Body is null"))
            } else {
                Result.failure(Exception("API Response Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Unknown Error: ${e.localizedMessage}"))
        }
    }
}
