package com.example.unishop.ui.screens
import com.example.unishop.data.entity.Product
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.unishop.R
import com.example.unishop.ui.theme.PoppinsFamily2


@Composable
fun ProductCard(
    product: Product,
    onProductClick: (Product) -> Unit,
    onAddToCart: (Product) -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onProductClick(product) }, colors = CardDefaults.cardColors(containerColor = colorResource(
            R.color.card))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            val fullImageUrl = "http://kasimadalan.pe.hu/urunler/resimler/${product.resim}"

            // Ürün Görseli
            Image(
                painter = rememberAsyncImagePainter(fullImageUrl),
                contentDescription = product.ad,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, Color.White, RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Ürün Bilgileri
            Text(
                text = product.marka,
                color = colorResource(R.color.text2),
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                maxLines = 1,
                fontFamily = PoppinsFamily2
            )
            Text(
                text = product.ad,
                color = colorResource(R.color.text1),
                fontWeight = FontWeight.SemiBold,
                fontSize = 17.sp,
                maxLines = 1,
                fontFamily = PoppinsFamily2
            )

            Row ( modifier = Modifier.fillMaxWidth().padding(start = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically){

                Text(
                    text = "${product.fiyat} ₺",
                    color = colorResource(R.color.text1),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    fontFamily = PoppinsFamily2
                )

                // Sepete Ekle Butonu
                IconButton(onClick = { onAddToCart(product) }) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Sepete Ekle",
                        tint = colorResource(R.color.primary)

                    )
                }

            }

        }
    }
}
