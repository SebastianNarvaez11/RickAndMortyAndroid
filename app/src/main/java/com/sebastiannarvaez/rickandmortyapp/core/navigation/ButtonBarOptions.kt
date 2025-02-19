package com.sebastiannarvaez.rickandmortyapp.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector


enum class TopLevelDestination(
    val icon: ImageVector,
    val label: String,
    val screen: Screen,
) {
    HOME(
        icon = Icons.Default.Home,
        label = "Home",
        screen = Screen.Home,
    ),
    POKEMON(
        icon = Icons.Default.Email,
        label = "Profile",
        screen = Screen.Pokemon,
    ),
}