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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.foodordering.foodiesh.viewModel.AuthViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.viewmodel.compose.viewModel
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
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),  // This will display 2 items per row
        modifier = Modifier.padding(16.dp)
    ) {
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
            product.imageUrl.let {
                Image(
                    model = product.imageUrl,  // The image URL from the product object
                    contentDescription = "Product Image", // Content description for accessibility
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)  // You can adjust this as needed
                        .padding(bottom = 8.dp),
                    contentScale = ContentScale.Crop // Crop the image to fit
                )
                Text(text = product.title, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = product.description, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Price: \$${product.price}", style = MaterialTheme.typography.bodyMedium)
            }
    }
}
}
