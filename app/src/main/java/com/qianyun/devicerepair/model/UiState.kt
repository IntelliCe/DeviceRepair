package com.qianyun.devicerepair.model

import androidx.compose.runtime.mutableStateOf

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Content<T>(val data: T) : UiState<T>()
    data class Error(val message: String? = null) : UiState<Nothing>()
}

fun <T> UiState<T>.isLoading() = this is UiState.Loading
fun <T> UiState<T>.isContent() = this is UiState.Content
fun <T> UiState<T>.isError() = this is UiState.Error

fun <T> UiState<T>.asContent() = this as UiState.Content
fun <T> UiState<T>.asError() = this as UiState.Error

fun <T> mutableUiStateOfContent(data: T) = mutableStateOf<UiState<T>>(UiState.Content(data))
fun <T> mutableUiStateOfLoading() = mutableStateOf<UiState<T>>(UiState.Loading)
fun <T> mutableUiStateOfError(message: String? = null) = mutableStateOf<UiState<T>>(UiState.Error(message))


