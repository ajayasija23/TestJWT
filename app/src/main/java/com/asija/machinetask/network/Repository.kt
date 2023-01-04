package com.asija.machinetask.network

import androidx.lifecycle.MutableLiveData
import com.asija.machinetask.model.ApiResponse
import java.net.UnknownHostException

class Repository {
    val apiData=MutableLiveData<NetworkResponse<ApiResponse>>()
    suspend fun getApiData(){
        val apiService=NetworkClient.getApiService()
        try {
            apiData.postValue(NetworkResponse.Loading())
            val response=apiService.getApiData()
            if (response.isSuccessful){
                apiData.postValue(NetworkResponse.Success(response.body()!!))
            }else{
                apiData.postValue(NetworkResponse.Error(false,response.message().orEmpty()))
            }
        } catch (e: Exception) {
            apiData.postValue(NetworkResponse.Error(e is UnknownHostException,e.localizedMessage.orEmpty()))
        }
    }
}