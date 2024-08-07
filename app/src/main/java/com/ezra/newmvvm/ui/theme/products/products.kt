package com.ezra.newmvvm.ui.theme.products

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.ezra.newmvvm.data.Product
import com.ezra.newmvvm.navigation.ROUTE_LOGIN
import com.ezra.newmvvm.navigation.ROUTE_REGISTER
import com.ezra.newmvvm.ui.theme.home.BottomBar
import com.ezra.newmvvm.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Products(navController: NavController, viewModel: ProductViewModel = viewModel()) {
    val products by remember { viewModel.products }
    val isLoading by remember { viewModel.isLoading }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchProducts()
    }


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Products") },
                modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars),
            )
        },
        content = {

            Column(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)) {
                if (isLoading) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator()
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "Loading products...")
                        }
                    }
                } else {
                    ProductGrid(products, navController)
                }
            }


        },
        bottomBar = { BottomBar(navController) }
    )

}

@Composable
fun ProductGrid(products: List<Product>, navController: NavController) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ) {
        items(products) { product ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        // Navigate to the product detail screen
                        navController.navigate("productDetail/${product.id}")
                    },
                elevation = 4.dp
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    AsyncImage(
                        model = product.image,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = product.product)
                    Text(text = "${product.amount}")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Products(rememberNavController())
}
