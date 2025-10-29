package com.example.unishop.data.entity

import com.google.gson.annotations.SerializedName

// SerializedName Retrofit ve Gson kütüphanelerinin, API'den gelen JSON anahtarları ile
// kotlin'de yazılan değişken isimlerini doğru şekilde eşleştirmesini sağlar.
// İncelediğim projelerde görüp kendi projeme de ekledim.
data class CartProduct (

    @SerializedName("sepetId")
    val sepetId: Int,
    @SerializedName("ad")
    val ad: String,
    @SerializedName("resim")
    val resim: String,
    @SerializedName("kategori")
    val kategori: String,
    @SerializedName("fiyat")
    val fiyat: Int,
    @SerializedName("marka")
    val marka: String,
    @SerializedName("siparisAdeti")
    val siparisAdeti: Int,
    @SerializedName("kullaniciAdi")
    val kullaniciAdi: String

) {
}