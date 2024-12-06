package com.foodordering.foodiesh.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.foodordering.foodiesh.viewModel.AuthViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.foodordering.foodiesh.model.Product
import com.foodordering.foodiesh.repository.AuthRepository
import com.foodordering.foodiesh.viewModel.AuthViewModelFactory

@Composable
fun ProductListScreen(authRepository: AuthRepository) {
    val viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(authRepository))
    val products by viewModel.products.observeAsState()
    val errorMessage by viewModel.errorMessage.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(false)


    LaunchedEffect(true) {
        viewModel.getProducts()
    }

    if (isLoading) {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
    } else if (errorMessage != null) {
        Text("Error: $errorMessage", modifier = Modifier.fillMaxSize())
    } else {
        products?.let { ProductList(products = it) }
    }
}

@Composable
fun ProductList(products: List<Product>) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(products) { product ->
            ProductItem(product = product)
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = product.title)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = product.description)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Price: \$${product.price}")
        }
    }
}
