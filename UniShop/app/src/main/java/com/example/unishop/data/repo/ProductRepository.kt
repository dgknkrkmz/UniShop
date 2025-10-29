package com.example.unishop.data.repo

import com.example.unishop.data.entity.CartResponse
import com.example.unishop.data.entity.ProductResponse
import com.example.unishop.data.entity.StatusResponse

interface ProductRepository {


    // Tüm ürünleri getiren fonksiyon
    suspend fun getAllProducts(): ProductResponse


    // Sepete ürün ekleme.
    suspend fun addToCart(
        ad: String,
        resim: String,
        kategori: String,
        fiyat: Int,
        marka: String,
        siparisAdeti: Int
    ): StatusResponse

    // Sepetteki ürünleri getirme. (Kullanıcı adı repository içinde sabit)
    suspend fun getCartProducts(): CartResponse

    // Sepetten ürün silme.
    suspend fun deleteFromCart(sepetId: Int): StatusResponse


}