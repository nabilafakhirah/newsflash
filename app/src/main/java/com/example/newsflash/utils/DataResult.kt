package com.example.newsflash.utils

sealed class DataResult<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null) : DataResult<T>(data)
    class Success<T>(data: T?) : DataResult<T>(data)
    class Error<T>(message: String, data: T? = null) : DataResult<T>(data, message)
}