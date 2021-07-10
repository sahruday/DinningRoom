package com.sahu.dinningroom.appUtil

sealed class ViewState<out T> {
    object Loading : ViewState<Nothing>()
    data class Error(val failure: Failure) : ViewState<Nothing>()
    data class Success<T>(val item: T) : ViewState<T>()

    fun isLoading() = this is Loading
    fun isSuccess() = this is Success
    fun isError() = this is Error
}