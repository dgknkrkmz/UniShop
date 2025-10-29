package com.example.unishop.ui.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.unishop.R


import com.example.unishop.data.entity.CartProduct
import com.example.unishop.ui.theme.PoppinsFamily
import com.example.unishop.ui.theme.PoppinsFamily2
import com.example.unishop.ui.viewmodel.CartViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController) {
    // Sepet verilerini yönetmek için ViewModel'i alıyoruz.
    val viewModel: CartViewModel = hiltViewModel()

    // ViewModel'deki ürün listesini ve toplam fiyatı dinliyoruz.
    val cartList by viewModel.cartProducts.collectAsState()
    val totalPrice by viewModel.totalPrice.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(
                    "Sepetim",
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = PoppinsFamily
                    )
                ) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Geri",
                            tint = colorResource(R.color.primary))
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = colorResource(com.example.unishop.R.color.background),
                    titleContentColor = colorResource(R.color.text1)
            )
            )
        },
        bottomBar = {
            // Sepet Toplam Tutar Alanı
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = colorResource(R.color.primary)),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Toplam Tutar:",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                        )
                        Text(
                            "$totalPrice ₺",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = {
                            println("Ödeme işlemi başlatıldı. Toplam Tutar: $totalPrice ₺")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White
                        ),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
                    ) {
                        Text("Ödemeye Geç",
                            fontSize = 16.sp,
                            color = colorResource(R.color.text2),

                        )
                    }
                }
            }
        },
        containerColor = colorResource(R.color.background)
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            if (cartList.isEmpty()) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Text("Sepetinizde ürün bulunmamaktadır.", fontSize = 18.sp,
                        color = colorResource(R.color.text1))
                }
            } else {
                LazyColumn(contentPadding = PaddingValues(16.dp)) {
                    items(cartList, key = { it.sepetId }) { cartItem ->
                        CartItemCard(
                            cartProduct = cartItem,
                            // Sepet ID'si ve ürün adını ViewModel'a gönderiyoruz.
                            onDelete = {
                                viewModel.deleteFromCart(cartItem.sepetId, cartItem.ad)
                            }
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}

// Sepet Ürünü Kartı
@Composable
fun CartItemCard(
    cartProduct: CartProduct,
    onDelete: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.card))

    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Ürün Resmi
            val fullImageUrl = "http://kasimadalan.pe.hu/urunler/resimler/${cartProduct.resim}"

            Image(
                painter = rememberAsyncImagePainter(fullImageUrl),
                contentDescription = cartProduct.ad,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(cartProduct.ad, fontWeight = FontWeight.Bold, fontSize = 16.sp, maxLines = 1,
                    color = colorResource(R.color.text1))
                Spacer(modifier = Modifier.height(4.dp))

                // Miktar ve Toplam Fiyat
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Adet: ${cartProduct.siparisAdeti}", color = colorResource(R.color.text2))
                    Text(
                        // Adet * Fiyat (Sepet içi toplamı)
                        "${cartProduct.fiyat * cartProduct.siparisAdeti} ₺",
                        color = colorResource(R.color.text1),
                        fontWeight = FontWeight.ExtraBold

                    )
                }
            }

            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Sepetten Sil",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
