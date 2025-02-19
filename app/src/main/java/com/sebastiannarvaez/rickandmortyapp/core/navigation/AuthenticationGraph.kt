package com.sebastiannarvaez.rickandmortyapp.core.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sebastiannarvaez.rickandmortyapp.modules.auth.presentation.screens.AuthViewModel
import com.sebastiannarvaez.rickandmortyapp.modules.auth.presentation.screens.LoginScreen
import com.sebastiannarvaez.rickandmortyapp.modules.auth.presentation.screens.RegisterScreen

fun NavGraphBuilder.authenticationGraph(navController: NavController, authVM: AuthViewModel) {
    composable<Screen.Login> {
        LoginScreen(
            authVM = authVM,
            onNavigateToHome = {
                navController.navigate(Screen.Home) {
                    popUpTo(Screen.Login) { inclusive = true }
                }
            }
        )
    }

    composable<Screen.Register> {
        RegisterScreen()
    }
}

