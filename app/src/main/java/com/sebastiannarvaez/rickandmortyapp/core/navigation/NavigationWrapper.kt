package com.sebastiannarvaez.rickandmortyapp.core.navigation

import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sebastiannarvaez.rickandmortyapp.modules.auth.presentation.screens.AuthState
import com.sebastiannarvaez.rickandmortyapp.modules.auth.presentation.screens.AuthViewModel


@Composable
fun NavigationWrapper(authVM: AuthViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val authState by authVM.authStatus.collectAsState()

    Scaffold(
        bottomBar = {
            //todo: esta condicion se puede quitar
            if (authState == AuthState.Authenticated) BottomBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home,
            modifier = Modifier.padding(innerPadding)
        ) {

            authenticationGraph(navController = navController, authVM = authVM)
            mainGraph(navController = navController, authVM = authVM)
        }
    }
}










