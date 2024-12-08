package com.foodordering.foodiesh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.foodordering.foodiesh.api.ApiService
import com.foodordering.foodiesh.api.RetrofitClient
import com.foodordering.foodiesh.repository.AuthRepository
import com.foodordering.foodiesh.view.LoginScreen
import com.foodordering.foodiesh.view.ProductListScreen
import com.foodordering.foodiesh.view.RegisterScreen
import com.foodordering.foodiesh.view.SplashScreen
import com.foodordering.foodiesh.viewModel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val apiService: ApiService = RetrofitClient.provideApiService(RetrofitClient.provideRetrofit())
            val authRepository =  AuthRepository(apiService)
            val viewModel = LoginViewModel(apiService)
            NavHost(navController = navController, startDestination = "splash") {
                composable("splash") { SplashScreen(navController) }
                composable("login") { LoginScreen(viewModel, navController) }
                composable("register") { RegisterScreen(navController,authRepository) }
                composable("product") { ProductListScreen(authRepository) }
            }
        }
    }
}

