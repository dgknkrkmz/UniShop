package com.example.unishop.data.entity

import com.google.gson.annotations.SerializedName


data class Product (

    @SerializedName("id")
    val id: Int,
    @SerializedName("ad")
    val ad: String,
    @SerializedName("resim")
    val resim: String,
    @SerializedName("kategori")
    val kategori: String,
    @SerializedName("fiyat")
    val fiyat: Int,
    @SerializedName("marka")
    val marka: String

){

}