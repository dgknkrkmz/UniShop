package com.example.unishop.data.datasources.remote

import com.example.unishop.data.entity.CartResponse
import com.example.unishop.data.entity.ProductResponse
import com.example.unishop.data.entity.StatusResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    // tüm ürünleri getirdiğim GET isteği
    @GET("urunler/tumUrunleriGetir.php")
    suspend fun getAllProducts(): ProductResponse


    // sepete ürün eklediğim post isteği
    @POST("urunler/sepeteUrunEkle.php")
    @FormUrlEncoded
    suspend fun addToCart(
        @Field("ad") ad: String,
        @Field("resim") resim: String,
        @Field("kategori") kategori: String,
        @Field("fiyat") fiyat: Int,
        @Field("marka") marka: String,
        @Field("siparisAdeti") siparisAdeti: Int,
        @Field("kullaniciAdi") kullaniciAdi: String
    ): StatusResponse


    // sepeti görüntüleme post isteğim
    @POST("urunler/sepettekiUrunleriGetir.php")
    @FormUrlEncoded
    suspend fun getCartProducts (
        @Field("kullaniciAdi") kullaniciAdi: String
    ): CartResponse


    // sepetten ürün silme post isteğim
    @POST("urunler/sepettenUrunSil.php")
    @FormUrlEncoded
    suspend fun deleteFromCart(
        @Field("sepetId") sepetId: Int,
        @Field("kullaniciAdi") kullaniciAdi: String
    ): StatusResponse
















}