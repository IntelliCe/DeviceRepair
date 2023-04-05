package com.qianyun.devicerepair.model

data class ResponseData<T>(
    val status: Int,
    val message: String?,
    val data: T
)

fun <T> ResponseData<T>.isSuccess(): Boolean = status == 200
