package com.qianyun.devicerepair.network

import com.qianyun.devicerepair.model.ResponseData
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

object AuthApi {
    suspend fun login(username: String, password: String): ResponseData<Boolean> {
        return AuthApiInstance.login(jsonBodyOf("username" to username, "password" to password))
    }
}

private val AuthApiInstance = retrofitOf(AuthApiInterface::class)

interface AuthApiInterface {
    @POST("auth/login")
    suspend fun login(@Body requestBody: RequestBody): ResponseData<Boolean>
}
