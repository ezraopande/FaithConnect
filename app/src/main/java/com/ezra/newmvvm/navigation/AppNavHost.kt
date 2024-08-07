package com.ezra.newmvvm.navigation

import SermonDetailsScreen
import com.ezra.newmvvm.ui.theme.slider.SliderScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ezra.newmvvm.ui.theme.SplashScreen
import com.ezra.newmvvm.ui.theme.auth.LoginScreen
import com.ezra.newmvvm.ui.theme.auth.RegistrationScreen
import com.ezra.newmvvm.ui.theme.dashboard.DashboardScreen
import com.ezra.newmvvm.ui.theme.home.Home
import com.ezra.newmvvm.ui.theme.products.ProductDetailScreen
import com.ezra.newmvvm.ui.theme.products.Products

@Composable
fun AppNavHost(

    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_HOME

){

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(ROUTE_PRODUCTS){
            Products(navController)
        }

        composable(ROUTE_HOME){
            Home(navController)
        }
        composable(ROUTE_SLIDES){
            SliderScreen(navController)
        }

        composable("productDetail/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            ProductDetailScreen(navController = navController,productId = productId)
        }

        composable(ROUTE_REGISTER){
            RegistrationScreen(navController)
        }

        composable(ROUTE_LOGIN){
            LoginScreen(navController = navController)
        }

        composable("dashboard/{userId}", arguments = listOf(navArgument("userId") { type = NavType.IntType })) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0
            DashboardScreen(navController, userId)
        }


        composable(ROUTE_SPLASH){
            SplashScreen(navController = navController)
        }


        composable("sermon_details/{sermonId}") { backStackEntry ->
            val sermonId = backStackEntry.arguments?.getString("sermonId")
            if (sermonId != null) {
                SermonDetailsScreen(
                    sermonId = sermonId,
                    navController = navController // Use the existing navController instance
                )
            }
        }

















    }

}