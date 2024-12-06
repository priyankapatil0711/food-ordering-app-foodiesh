package com.foodordering.foodiesh.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.foodordering.foodiesh.R
import com.foodordering.foodiesh.model.LoginRequest
import com.foodordering.foodiesh.repository.AuthRepository
import com.foodordering.foodiesh.utility.Resource
import com.foodordering.foodiesh.viewModel.AuthViewModel
import com.foodordering.foodiesh.viewModel.AuthViewModelFactory

@Composable
fun LoginScreen(navController: NavController, authRepository: AuthRepository) {
    val viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(authRepository))

    val loginResult by viewModel.loginResult.observeAsState(Resource.Loading())
    val isLoading by viewModel.isLoading.observeAsState(false)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.login), // Replace with your image resource
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // This ensures the image covers the screen
        )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally, // Horizontally center content
        verticalArrangement = Arrangement.Center // Vertically center content
    ) {
        Text(
            text = "Login",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 50.dp)
        )


        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(16.dp))


        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.login(email, password) },
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth()

        ) {
            Text("Login")
        }


        Spacer(modifier = Modifier.height(70.dp))
        TextButton(
            onClick = {navController.navigate("register")}
        ) {Text(text = "Don't have an account? Sign Up", color = Color.Black)}


        Spacer(modifier = Modifier.height(70.dp))
        TextButton(
            onClick = {navController.navigate("product")}
        ) {Text(text = "Go to Product screen", color = Color.Black)}

        when (loginResult) {
            is Resource.Loading -> {
                CircularProgressIndicator()
            }
            is Resource.Success -> {
                val response = (loginResult as Resource.Success).data
                Text("Login Successful! Welcome")
            }
            is Resource.Error -> {
                val message = (loginResult as Resource.Error).message
                Text("Error: $message")
            }
        }
    }
}}

