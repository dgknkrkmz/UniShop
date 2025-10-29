package com.example.unishop.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unishop.data.entity.Product
import com.example.unishop.data.repo.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    // API'den çekilen tüm ürünlerin orijinal listesi
    private val _allProducts = MutableStateFlow<List<Product>>(emptyList())

    // Arama metni
    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText

    // Filtrelenmiş ürün listesi
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    init {
        getAllProducts()
        // Ürün listesi ve arama metnini birleştirip filtreleme işlemini yönetiyoruz
        viewModelScope.launch {
            combine(_allProducts, _searchText) { all, query ->
                if (query.isBlank()) {
                    all
                } else {
                    all.filter {
                        it.ad.contains(query, ignoreCase = true) ||
                                it.marka.contains(query, ignoreCase = true) ||
                                it.kategori.contains(query, ignoreCase = true)
                    }
                }
            }.collect {
                _products.value = it
            }
        }
    }

    // Ürünleri API'den çekme
    fun getAllProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getAllProducts()
                if (response.success == 1) {
                    _allProducts.value = response.products ?: emptyList()
                    Log.d("HomeViewModel", "Ürünler başarıyla çekildi: ${_allProducts.value.size} adet")
                } else {
                    Log.e("HomeViewModel", "Ürün çekme başarısız: ${response.message}")
                    _allProducts.value = emptyList()
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Ürün çekme hatası: ${e.localizedMessage}")

            }
        }
    }

    // Arama metnini güncelleme
    fun updateSearchText(newText: String) {
        _searchText.value = newText
    }

    // Sepete ürün ekleme
    fun addToCart(
        ad: String,
        resim: String,
        kategori: String,
        fiyat: Int,
        marka: String,
        siparisAdeti: Int
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.addToCart(ad, resim, kategori, fiyat, marka, siparisAdeti)
                if (response.success == 1) {
                    Log.i("HomeViewModel", "$ad sepete başarıyla eklendi. Mesaj: ${response.message}")
                } else {
                    Log.e("HomeViewModel", "$ad sepete eklenirken hata: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Sepete ekleme hatası: ${e.localizedMessage}")
            }
        }
    }
}
