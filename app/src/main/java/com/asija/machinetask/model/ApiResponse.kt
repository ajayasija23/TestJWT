package com.asija.machinetask.model


import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("data")
    var `data`: List<Data>,
    @SerializedName("message")
    var message: String,
    @SerializedName("status")
    var status: Int,
    @SerializedName("success")
    var success: Boolean,
    @SerializedName("token")
    var token: String
)