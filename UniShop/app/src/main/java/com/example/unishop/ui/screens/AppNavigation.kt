package com.example.unishop.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.example.unishop.data.entity.Product

// Navigasyon Rotaları
object Screens {
    const val HOME = "home"
    const val CART = "cart"
    // Detay sayfasına Product objesini JSON olarak göndereceğiz
    const val DETAIL = "detail/{productJson}"
}

// Ana Navigasyon Yapısı
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.HOME) {

        composable(Screens.HOME) {
            HomeScreen(navController = navController)
        }

        composable(Screens.CART) {
             CartScreen(navController = navController)
        }

        // Detay sayfasına geçiş
        composable(
            Screens.DETAIL,
            arguments = listOf(navArgument("productJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val productJson = backStackEntry.arguments?.getString("productJson")

            productJson?.let {
                // JSON stringini Product objesine dönüştürüyoruz
                val product = Gson().fromJson(it, Product::class.java)

                 DetailScreen(navController = navController, product = product)
            }
        }
    }
}
