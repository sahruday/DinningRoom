package com.sahu.dinningroom.appUtil

sealed class Callback {
    object Success: Callback()
    data class Error(val error: String?) : Callback()
}

class MyResult<out T> constructor(
    private val value: Any?
) {
    val isSuccess: Boolean get() = value !is Throwable

    companion object {
        fun <T> failure(exception: Throwable): MyResult<T> =
            MyResult(exception)

        fun <T> success(value: T): MyResult<T> =
            MyResult(value)
    }

    suspend fun handle(onSuccess: suspend (T) -> Unit = {}, onFailure: suspend (Throwable) -> Unit = {}) {
        if (isSuccess) {
            onSuccess(value as T)
        } else {
            onFailure(value as Throwable)
        }
    }
}