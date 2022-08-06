package com.mydemo.usercomments.network

sealed class NetworkResponse<T>(val data: T? = null, val message: String? = null, val errorCode: Int? = 0) {
    class Success<T>(data: T?) : NetworkResponse<T>(data)
    class Error<T>(message: String,errorCode:Int, data: T? = null) : NetworkResponse<T>(data, message, errorCode)
    class Loading<T> : NetworkResponse<T>()
}