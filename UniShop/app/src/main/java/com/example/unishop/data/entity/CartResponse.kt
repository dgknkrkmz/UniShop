package com.example.unishop.data.entity

import com.google.gson.annotations.SerializedName
// SerializedName Retrofit ve Gson kütüphanelerinin, API'den gelen JSON anahtarları ile
// kotlin'de yazılan değişken isimlerini doğru şekilde eşleştirmesini sağlar.
// İncelediğim projelerde görüp kendi projeme de ekledim.

data class CartResponse(
    @SerializedName("urunler_sepeti")
    val cartProducts: List<CartProduct>,
    @SerializedName("success")
    val success: Int
) {
}