package com.sebastiannarvaez.rickandmortyapp.modules.auth.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun LoginScreen(authVM: AuthViewModel, onNavigateToHome: () -> Unit) {
    val authState by authVM.authStatus.collectAsState()

    LaunchedEffect(authState) {
        if (authState == AuthState.Authenticated) {
            onNavigateToHome()
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(text = "Login screen")
            Button(onClick = {
                authVM.login("sebas@gmail.com", password = "123456")
            }) {
                Text(text = "Iniciar sesion")
            }
        }
    }
}