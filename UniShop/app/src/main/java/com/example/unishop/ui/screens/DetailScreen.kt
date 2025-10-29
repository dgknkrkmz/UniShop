package com.example.unishop.ui.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.unishop.R
import com.example.unishop.data.entity.Product
import com.example.unishop.ui.theme.PoppinsFamily
import com.example.unishop.ui.theme.PoppinsFamily2
import com.example.unishop.ui.viewmodel.HomeViewModel
import java.time.format.TextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController, product: Product) {
    // Sepete ekleme işlemi için HomeViewModel'i kullanıyoruz
    val viewModel: HomeViewModel = hiltViewModel()

    // Sipariş adeti için yerel durum (state). Minimum 1 olmalı.
    var adet by remember { mutableIntStateOf(1) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(
                    product.ad,
                    style = androidx.compose.ui.text.TextStyle(
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
                    containerColor = colorResource(R.color.background),
                    titleContentColor = colorResource(R.color.text1)
                )
            )
        },
        bottomBar = {
            // Sepete Ekle Butonu
            Button(
                onClick = {
                    // Seçilen adet ile sepete ekleme işlemini başlat
                    viewModel.addToCart(
                        product.ad, product.resim, product.kategori, product.fiyat, product.marka, adet
                    )
                    // Sepete ekledikten sonra bir önceki ekrana geri git
                    navController.popBackStack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.primary)
                ),
                shape = RoundedCornerShape(24.dp),
                contentPadding = PaddingValues(vertical = 14.dp),
                enabled = adet > 0 // Adet 0'dan büyükse buton aktif
            ) {
                Text("Sepete Ekle (${product.fiyat * adet} ₺)", style = MaterialTheme.typography.titleMedium)
            }
        },
        containerColor = colorResource(R.color.background)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            // Ürün Resmi
            val fullImageUrl = "http://kasimadalan.pe.hu/urunler/resimler/${product.resim}"
            Divider(modifier = Modifier.padding(vertical = 19.dp),
                color = colorResource(R.color.primary))

            Image(
                painter = rememberAsyncImagePainter(fullImageUrl),
                contentDescription = product.ad,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Ürün Başlık ve Fiyat
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    product.ad,
                    color = colorResource(R.color.text1),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    fontSize = 27.sp,

                )
                Text(
                    "${product.fiyat} ₺",
                    style = MaterialTheme.typography.headlineMedium,
                    color = colorResource(R.color.primary),
                    fontWeight = FontWeight.ExtraBold

                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "Marka: ${product.marka} | Kategori: ${product.kategori}",
                style = MaterialTheme.typography.bodyLarge,
                color = colorResource(R.color.text2),
                fontFamily = PoppinsFamily2
            )

            Divider(modifier = Modifier.padding(vertical = 19.dp),
                color = colorResource(R.color.primary))

            // Açıklama
            Text(
                "Ürün Açıklaması",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.text1),
                fontFamily = PoppinsFamily2
            )
            Text(
                "Bu ürün, kategorisinin en çok satan ve en kaliteli ürünlerinden biridir. Sağlam yapısı, uzun ömürlü kullanımı ve yüksek performansı ile günlük ihtiyaçlarınız için mükemmel bir çözümdür. Tüm detaylar ve teknik özellikler için lütfen üretici sitesine bakınız.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 8.dp),
                color = colorResource(R.color.text2),

            )

            Divider(modifier = Modifier.padding(vertical = 19.dp),
                color = colorResource(R.color.primary))

            // Adet Seçimi
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Adet Seçimi:",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(R.color.text1),
                    fontFamily = PoppinsFamily2
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Azalt butonu
                    IconButton(
                        onClick = { if (adet > 1) adet-- },
                        enabled = adet > 1,
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = colorResource(R.color.primary).copy(alpha = if (adet > 1) 1f else 0.5f)
                        )
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_minimize),
                            contentDescription = "Azalt",
                            modifier = Modifier.size(20.dp),
                            tint = colorResource(R.color.text1).copy(alpha = if (adet > 1) 1f else 0.5f),
                        )
                    }

                    Text(
                        "$adet",
                        modifier = Modifier.padding(horizontal = 16.dp),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = colorResource(R.color.text1)
                    )

                    IconButton(
                        onClick = { adet++ },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = colorResource(R.color.primary)
                        )
                    ) {
                        Icon(Icons.Default.Add,
                            contentDescription = "Arttır",
                            modifier = Modifier.size(20.dp),
                            tint = colorResource(
                            R.color.text1))
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}
