package com.ezra.newmvvm.ui.theme.products

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ezra.newmvvm.ui.theme.home.BottomBar
import com.ezra.newmvvm.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProductDetailScreen(navController:NavController, productId: String?, viewModel: ProductViewModel = viewModel()) {
    val product by viewModel.selectedProduct

    LaunchedEffect(productId) {
        productId?.let { viewModel.fetchProductById(it) }
    }



    Scaffold(

        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Details")},
                modifier = Modifier .windowInsetsPadding(WindowInsets.systemBars),
            )
        },

        content = {
                        product?.let {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)
                            ) {
                                AsyncImage(
                                    model = it.image,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(250.dp)
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(text = it.product, style = MaterialTheme.typography.h5)
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(text = "${it.amount}", style = MaterialTheme.typography.h6)
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(text = it.quantity, style = MaterialTheme.typography.h6)
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(text = it.description, style = MaterialTheme.typography.h6)
                                Spacer(modifier = Modifier.height(8.dp))

                            }
                        } ?: run {
                            Text("Loading...")
                        }
                    },


        bottomBar = { BottomBar(navController) }


    )





}
