package com.example.tmdb.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.tmdb.Utils.MediaType

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Movie.route
    ) {
        composable(route = BottomBarScreen.Movie.route) {
            MediasScreen(mediaType = MediaType.MOVIE)
        }
        composable(route = BottomBarScreen.Serie.route) {
            MediasScreen(mediaType = MediaType.SERIE)
        }
        composable(route = BottomBarScreen.Person.route) {
            PersonScreen()
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Movie,
        BottomBarScreen.Serie,
        BottomBarScreen.Person
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route
    NavigationBar {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: String?,
    navController: NavHostController
) {
    NavigationBarItem(
        selected = currentDestination == screen.route ,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                screen.selectedIcon,
                contentDescription = screen.title
            )
        },
        alwaysShowLabel = false
    )
}