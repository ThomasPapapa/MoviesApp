package com.thomas.data.common

/**
 * A generic class that holds a value or an error status.
 * @param <T>
 */
sealed class ResponseResult<out R> {
    data class Success<out T>(val data: T?) : ResponseResult<T>()
    data class Error(val exception: Exception) : ResponseResult<Nothing>()
}