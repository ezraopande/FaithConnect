package com.ezra.newmvvm.ui.theme.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ezra.newmvvm.viewmodel.LoginViewModel
import com.ezra.newmvvm.data.auth.User

import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DashboardScreen(navController: NavController, userId: Int, viewModel: LoginViewModel = viewModel()) {
    var user by remember { mutableStateOf<User?>(null) }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(userId) {
        viewModel.getUserDetails(userId) { fetchedUser, error ->
            if (fetchedUser != null) {
                user = fetchedUser
            } else {
                errorMessage = error ?: "Unknown error"
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (user != null) {
                Text("Welcome to the Dashboard, ${user!!.first_name} ${user!!.last_name}")
                Text("Email: ${user!!.email}")
                Text("Gender: ${user!!.gender}")



                // Add more user details here
            } else if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = MaterialTheme.colors.error)
            } else {
                CircularProgressIndicator()
                Text(text = "Loading Details")
            }
        }
    }
}
