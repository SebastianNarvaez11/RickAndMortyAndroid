package com.sebastiannarvaez.rickandmortyapp.core.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    //Pantalla de autenticacion
    @Serializable
    data object Login : Screen

    @Serializable
    data object Register : Screen

    @Serializable
    data object Splash : Screen

    //Pantallas de la button bar
    @Serializable
    data object Home : Screen

    @Serializable
    data object Pokemon : Screen

    //Pantallas sin la bottom bar
    @Serializable
    data object Detail : Screen

    @Serializable
    data class PokemonDetail(val pokemonName: String) : Screen
}