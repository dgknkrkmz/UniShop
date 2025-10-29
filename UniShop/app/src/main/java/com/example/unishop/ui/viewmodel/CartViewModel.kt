package com.example.unishop.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unishop.data.entity.CartProduct
import com.example.unishop.data.repo.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    // Sepetteki ürün listesi
    private val _cartProducts = MutableStateFlow<List<CartProduct>>(emptyList())
    val cartProducts: StateFlow<List<CartProduct>> = _cartProducts

    // Sepet toplam fiyatı
    private val _totalPrice = MutableStateFlow(0)
    val totalPrice: StateFlow<Int> = _totalPrice

    init {
        getCartProducts()
        // Sepet listesi her güncellendiğinde toplam fiyatı hesapladığımız kısım
        viewModelScope.launch {
            _cartProducts.collect { list ->
                // Her ürünün adeti ile fiyatını çarpıp hepsini toplar.
                _totalPrice.value = list.sumOf { it.fiyat * it.siparisAdeti }
            }
        }
    }

    // Sepetteki ürünleri API'den çekme
    fun getCartProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getCartProducts()
                if (response.success == 1 && response.cartProducts != null) {
                    _cartProducts.value = response.cartProducts
                    Log.d("CartViewModel", "Sepet başarıyla çekildi: ${_cartProducts.value.size} adet")
                } else {
                    Log.e("CartViewModel", "Sepet çekme başarısız: Success=${response.success}")
                    _cartProducts.value = emptyList()
                }
            } catch (e: Exception) {
                Log.e("CartViewModel", "Sepet çekme hatası: ${e.localizedMessage}")
                _cartProducts.value = emptyList()
            }
        }
    }

    // Sepetten ürün silme
    fun deleteFromCart(sepetId: Int, ad: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.deleteFromCart(sepetId)
                if (response.success == 1) {
                    Log.i("CartViewModel", "$ad sepettek silindi. Mesaj: ${response.message}")
                    // Silme başarılı olduktan sonra sepet listesini yeniden yükle
                    getCartProducts()
                } else {
                    Log.e("CartViewModel", "$ad silinirken hata: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e("CartViewModel", "Sepetten silme hatası: ${e.localizedMessage}")
            }
        }
    }
}
