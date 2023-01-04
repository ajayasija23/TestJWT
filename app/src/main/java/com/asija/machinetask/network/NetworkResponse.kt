package com.asija.machinetask.network


sealed class NetworkResponse<T> {

    class Success<T>(val data: T) : NetworkResponse<T>()

    class Error<T>(val internet:Boolean,val msg:String) : NetworkResponse<T>()

    class Loading<T> : NetworkResponse<T>()

}