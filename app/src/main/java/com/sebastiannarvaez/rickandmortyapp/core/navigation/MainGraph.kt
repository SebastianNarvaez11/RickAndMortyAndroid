package com.sebastiannarvaez.rickandmortyapp.core.navigation

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.sebastiannarvaez.rickandmortyapp.modules.auth.presentation.screens.AuthViewModel
import com.sebastiannarvaez.rickandmortyapp.modules.pokemon.presentation.screens.detail.PokemonDetailScreen
import com.sebastiannarvaez.rickandmortyapp.modules.pokemon.presentation.screens.pokemon.PokemonScreen
import com.sebastiannarvaez.rickandmortyapp.modules.rickandmorty.presentation.screens.detail.DetailScreen
import com.sebastiannarvaez.rickandmortyapp.modules.rickandmorty.presentation.screens.home.HomeScreen

fun NavGraphBuilder.mainGraph(navController: NavController, authVM: AuthViewModel) {
    authenticatedComposable<Screen.Home>(navController = navController, authVM = authVM) {
        HomeScreen()
    }

    authenticatedComposable<Screen.Pokemon>(
        navController = navController,
        authVM = authVM,
        deepLinks = listOf(navDeepLink { uriPattern = "https://sebastiannarvaez.com/pokemons" })
    ) {
        PokemonScreen(goToDetail = { name ->
            navController.navigate(Screen.PokemonDetail(name))
        })
    }

    composable<Screen.Detail> {
        DetailScreen()
    }

    authenticatedComposable<Screen.PokemonDetail>(
        navController = navController,
        authVM = authVM,
        deepLinks = listOf(navDeepLink {
            uriPattern = "https://sebastiannarvaez.com/pokemons/detail/{pokemonName}"
        })
    ) { backStackEntry ->
        // Extraer el parámetro "name" del deep link
        val nameFromDeepLink = backStackEntry.arguments?.getString("pokemonName").orEmpty()

        // Extraer el parámetro "name" de los argumentos de navegación
        val nameFromNavArgs = backStackEntry.toRoute<Screen.PokemonDetail>().pokemonName

        // Usar el valor que esté disponible
        val name = nameFromDeepLink ?: nameFromNavArgs

        PokemonDetailScreen(name = name)
    }

//    navegar con parametros sin ruta protegida
//    composable<Screen.PokemonDetail> { backStackEntry ->
//        val detail: Screen.PokemonDetail = backStackEntry.toRoute()
//
//        PokemonDetailScreen(name = detail.pokemonName)
//    }
}
