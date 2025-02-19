package com.sebastiannarvaez.rickandmortyapp.core.network

import retrofit2.HttpException
import java.io.IOException

sealed class ApiResponse<out T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Error(val message: String, val statusCode: Int) : ApiResponse<Nothing>()
}

fun handleApiError(e: Exception): ApiResponse.Error {
    return when (e) {
        is HttpException -> {
            val code = e.code()
            val message = e.response()?.errorBody()?.string() ?: "Unknown error"
            ApiResponse.Error(message, code)
        }

        is IOException -> ApiResponse.Error("Network error: Unable to reach the server", 503)
        else -> ApiResponse.Error("Unexpected error: ${e.localizedMessage}", 500)
    }
}