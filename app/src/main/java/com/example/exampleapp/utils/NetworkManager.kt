package com.example.exampleapp.utils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkManager {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"
    private const val CONNECT_TIMEOUT = 15000L

    fun getRetrofit(): Retrofit =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(
            RxJava3CallAdapterFactory.create()
        ).baseUrl(
            BASE_URL
        ).client(getNetworkClient()).build()


    private fun getNetworkClient(): OkHttpClient = OkHttpClient.Builder().addNetworkInterceptor {
        it.proceed(it.request().newBuilder().addHeader("testheader", "testheader").build())
    }.retryOnConnectionFailure(true).connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS).build()
}