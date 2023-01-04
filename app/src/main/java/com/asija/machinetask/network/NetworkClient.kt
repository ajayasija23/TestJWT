package com.asija.machinetask.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object NetworkClient {
    val baseUrl="https://mindmyscape.localserverpro.com"

    fun createHttpClient(): OkHttpClient {
        val logger=HttpLoggingInterceptor()
        logger.level=HttpLoggingInterceptor.Level.BASIC
        val httpClient=OkHttpClient.Builder()
            .addInterceptor(logger)
            .readTimeout(1000,TimeUnit.MINUTES)
            .writeTimeout(1000,TimeUnit.MINUTES)
            .connectTimeout(1000,TimeUnit.MINUTES)
            .build()
        return httpClient
    }
    fun createRetrofitClient(): Retrofit {
        val httpClient= createHttpClient()
        val retrofit=Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    fun getApiService(): ApiInterface {
        return createRetrofitClient().create(ApiInterface::class.java)
    }
}