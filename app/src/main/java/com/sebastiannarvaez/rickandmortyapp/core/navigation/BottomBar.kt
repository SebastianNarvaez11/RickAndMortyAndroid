package com.sebastiannarvaez.rickandmortyapp.core.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(
    navController: NavHostController,
) {
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentDestination = navBackStackEntry?.destination

    val isTopLevelDestination = TopLevelDestination.entries.any { topLevel ->
        currentDestination?.hierarchy?.any {
            it.hasRoute(topLevel.screen::class)
        } == true
    }

    if (isTopLevelDestination) {

        NavigationBar {
            TopLevelDestination.entries.forEach { item ->

                val selected = currentDestination?.hierarchy?.any {
                    it.hasRoute(item.screen::class)
                } == true

                NavigationBarItem(
                    icon = {
                        Icon(
                            item.icon,
                            contentDescription = item.name
                        )
                    },
                    label = { Text(item.label) },
                    selected = selected,
                    onClick = {
                        navController.navigate(item.screen) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}