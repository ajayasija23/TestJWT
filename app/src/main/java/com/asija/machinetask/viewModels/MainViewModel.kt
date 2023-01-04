package com.asija.machinetask.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asija.machinetask.model.ApiResponse
import com.asija.machinetask.network.NetworkResponse
import com.asija.machinetask.network.Repository
import kotlinx.coroutines.launch

class MainViewModel :ViewModel() {
    private val repository=Repository()
    val apiData:LiveData<NetworkResponse<ApiResponse>>

    init {
        apiData=repository.apiData
    }

    fun getApiData(){
       viewModelScope.launch {
           repository.getApiData()
       }
    }
}