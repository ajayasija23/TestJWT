package com.asija.machinetask.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("created_at")
    var createdAt: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("image_nm")
    var imageNm: String,
    @SerializedName("image_path")
    var imagePath: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("profile")
    var profile: String,
    @SerializedName("status")
    var status: String,
    @SerializedName("updated_at")
    var updatedAt: String
)