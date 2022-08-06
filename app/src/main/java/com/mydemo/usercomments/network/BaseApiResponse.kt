package com.mydemo.usercomments.network

import retrofit2.Response

abstract class BaseApiResponse {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResponse<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResponse.Success(body)
                }
            }
            return error("${response.code()} ${response.message()}",response.code())
        } catch (e: Exception) {
            return error(e.message ?: e.toString(),500)
        }
    }

    private fun <T> error(errorMessage: String,resCode:Int): NetworkResponse<T> =
        NetworkResponse.Error("Api call failed $errorMessage",resCode)

}