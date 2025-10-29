package com.example.unishop.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.unishop.R
import com.example.unishop.ui.theme.PoppinsFamily
import com.example.unishop.ui.theme.PoppinsFamily2
import com.example.unishop.ui.viewmodel.HomeViewModel
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    // ViewModel'i Hilt üzerinden alıyoruz
    val viewModel: HomeViewModel = hiltViewModel()

    // Ürün listesi ve arama metnini dinliyorum
    val productList by viewModel.products.collectAsState()
    val searchText by viewModel.searchText.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(
                    "SHOP",
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = PoppinsFamily
                    )

                ) },

                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = colorResource(R.color.background),
                    titleContentColor = colorResource(R.color.primary)
                ),
                actions = {
                    // Sepet butonu
                    IconButton(onClick = { navController.navigate(Screens.CART) }) {
                        Icon(imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Sepet",
                            tint = colorResource(R.color.text1),
                            modifier = Modifier.size(32.dp))
                    }
                }
            )
        },
        containerColor = colorResource(R.color.background)
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {

            // Arama Kutusu
            OutlinedTextField(
                value = searchText,
                onValueChange = { viewModel.updateSearchText(it) },
                label = { Text("Ürün veya marka ara...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Arama") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedBorderColor = colorResource(R.color.text1),
                    focusedBorderColor = colorResource(R.color.primary),
                    unfocusedLeadingIconColor = colorResource(R.color.text1),
                    focusedLeadingIconColor = colorResource(R.color.primary),
                    unfocusedLabelColor = colorResource(R.color.text1),
                    focusedLabelColor = colorResource(R.color.primary)
                )

            )

            // Yükleme, Hata veya Liste Durumu
            if (productList.isEmpty() && searchText.isBlank()) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator() // Yükleniyor
                }
            } else if (productList.isEmpty() && searchText.isNotBlank()) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Text("Aradığınız ürün bulunamadı.", fontSize = 18.sp, color = colorResource(R.color.text1))
                }
            } else {
                // Ürün Listesi
                LazyVerticalGrid (
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(productList) { product ->
                        ProductCard(
                            product = product,
                            onProductClick = {
                                val productJson = Gson().toJson(product)
                                navController.navigate("detail/${productJson}")
                            },
                            onAddToCart = {
                                viewModel.addToCart(
                                    it.ad, it.resim, it.kategori, it.fiyat, it.marka, siparisAdeti = 1
                                )
                            }
                        )
                    }
                }




            }
        }
    }
}
