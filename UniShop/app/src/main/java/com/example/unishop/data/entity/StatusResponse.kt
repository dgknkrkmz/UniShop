package com.example.unishop.data.entity

import com.google.gson.annotations.SerializedName

// başarı ve hata cevabı almak için sınıf
data class StatusResponse (
    @SerializedName("success")
    val success: Int,
    @SerializedName("message")
    val message: String
){
}