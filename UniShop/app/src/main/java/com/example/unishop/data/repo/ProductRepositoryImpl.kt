package com.example.unishop.data.repo

import com.example.unishop.data.datasources.remote.ApiService
import com.example.unishop.data.entity.CartResponse
import com.example.unishop.data.entity.ProductResponse
import com.example.unishop.data.entity.StatusResponse
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    ProductRepository {
    // Kullanıcı adını tek bir yerde tanımlıyoruz. ViewModel'a bu bilgiyi göndermeyeceğim
    private val USERNAME = "dogukan_korkmaz"

    override suspend fun getAllProducts(): ProductResponse {

        return apiService.getAllProducts()
    }

    override suspend fun addToCart(
        ad: String,
        resim: String,
        kategori: String,
        fiyat: Int,
        marka: String,
        siparisAdeti: Int
    ): StatusResponse {
        // ViewModel'dan gelen ürün bilgilerini alıp ve sabit USERNAME'i ekleyerek API'ye gönderiyorum.
        return apiService.addToCart(ad, resim, kategori, fiyat, marka, siparisAdeti, USERNAME)
    }

    override suspend fun getCartProducts(): CartResponse {
        // Sadece sabit USERNAME'i göndererek sepeti çeker.
        return apiService.getCartProducts(USERNAME)
    }

    override suspend fun deleteFromCart(sepetId: Int): StatusResponse {
        // Sepet ID'si ve USERNAME'i göndererek silme işlemini yapar.
        return apiService.deleteFromCart(sepetId, USERNAME)
    }

}