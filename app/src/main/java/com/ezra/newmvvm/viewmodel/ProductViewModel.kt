package com.ezra.newmvvm.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezra.newmvvm.data.Product
import com.ezra.newmvvm.network.RetrofitInstance
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val api = RetrofitInstance.api
    var products = mutableStateOf<List<Product>>(listOf())
        private set

    var isLoading = mutableStateOf(false)
        private set

    var selectedProduct = mutableStateOf<Product?>(null)
        private set

    fun fetchProducts() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                products.value = api.getProducts()
            } catch (e: Exception) {
                // Handle error
            } finally {
                isLoading.value = false
            }
        }
    }

    fun fetchProductById(productId: String) {
        viewModelScope.launch {
            try {
                selectedProduct.value = api.getProductById(productId)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }


}
