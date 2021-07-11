package com.sahu.dinningroom.appUtil

sealed class Callback {
    object Success: Callback()
    data class Error(val error: String?) : Callback()
}

class AppResult<out T> constructor(
    private val value: Any?
) {
    val isSuccess: Boolean get() = value !is Throwable

    companion object {
        fun <T> failure(exception: Throwable): AppResult<T> =
            AppResult(exception)

        fun <T> success(value: T): AppResult<T> =
            AppResult(value)
    }

    suspend fun handle(onSuccess: suspend (T) -> Unit = {}, onFailure: suspend (Throwable) -> Unit = {}) {
        if (isSuccess) {
            onSuccess(value as T)
        } else {
            onFailure(value as Throwable)
        }
    }
}