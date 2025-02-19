package com.sebastiannarvaez.rickandmortyapp.modules.auth.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SplashScreen(authVM: AuthViewModel = hiltViewModel()) {
    Column {
        Text(text = "splash screen")
        Button(onClick = { authVM.login(email = "sebas@gmail.com", password = "123456") }) {
            Text(text = "Login")
        }
        Button(onClick = { authVM.logout() }) {
            Text(text = "Logout")
        }
    }
}