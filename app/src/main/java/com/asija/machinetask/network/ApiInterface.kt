package com.asija.machinetask.network

import com.asija.machinetask.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("api/doctor/therapies")
    suspend fun getApiData():Response<ApiResponse>
}