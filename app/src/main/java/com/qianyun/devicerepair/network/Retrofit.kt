package com.qianyun.devicerepair.network

import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import kotlin.reflect.KClass

private val retrofitClient by lazy {
    Retrofit.Builder()
        .baseUrl("http://101.43.20.77:15001/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun <T : Any> retrofitOf(api: KClass<T>): T = retrofitClient.create(api.java)

fun jsonBodyOf(vararg pairs: Pair<String, String>): RequestBody = RequestBody.create(MediaType.parse("application/json"), Gson().toJson(mapOf(*pairs)))

const val NetworkErrorString = "网络连接失败，请检查网络连接"
