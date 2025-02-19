package com.sebastiannarvaez.rickandmortyapp.core.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sebastiannarvaez.rickandmortyapp.modules.auth.presentation.screens.AuthState
import com.sebastiannarvaez.rickandmortyapp.modules.auth.presentation.screens.AuthViewModel

inline fun <reified T : Any> NavGraphBuilder.authenticatedComposable(
    navController: NavController,
    authVM: AuthViewModel,
    deepLinks: List<NavDeepLink> = emptyList(),
    noinline content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable<T>(
        deepLinks = deepLinks
    ) { navBackStackEntry ->
        val authenticationState by authVM.authStatus.collectAsState()

        when (authenticationState) {
            AuthState.Checking -> {
                LoadingView(authVM = authVM)
            }

            AuthState.Authenticated -> {
                content(navBackStackEntry)
            }

            AuthState.Unauthenticated -> {
                LaunchedEffect(authenticationState) {
                    navController.navigate(Screen.Login) {
                        popUpTo(navBackStackEntry.destination.route!!) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingView(authVM: AuthViewModel) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            CircularProgressIndicator(color = Color.Blue)
            Button(onClick = { authVM.login(email = "sebas@gmail.com", password = "123456") }) {
                Text(text = "Authenticated")
            }
            Button(onClick = { authVM.logout() }) {
                Text(text = "Unauthenticated")
            }
        }
    }
}
