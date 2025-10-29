package com.example.unishop.data.entity

import com.google.gson.annotations.SerializedName

// API'den gelen genel ürün listesi cevabını tanımladığım sınıfım
data class ProductResponse(
    @SerializedName("urunler")
    val products: List<Product>?,
    @SerializedName("success")
    val success: Int,
    // Sunucu beklenmedik bir hata mesajı döndürürse diye eklenen opsiyonel alan.
    @SerializedName("message")
    val message: String? = null // Hata logları için
) {
}
