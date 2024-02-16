package com.example.tmdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.tmdb.ui.components.BottomBar
import com.example.tmdb.ui.components.BottomNavGraph
import com.example.tmdb.ui.theme.TMDBTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TMDBTheme(darkTheme = false) {

                val navController = rememberNavController()

                // A surface container using the 'background' color from the theme
                Surface(
                    Modifier
                        .background(color = Color.LightGray)
                ) {
                    Scaffold(
                        bottomBar = {
                            BottomBar(navController = navController)
                        }
                    ) { paddingValues ->
                        Column(
                            Modifier
                                .fillMaxSize()
                                .padding(bottom = paddingValues.calculateBottomPadding() - 12.dp)
                        ) {

                            BottomNavGraph(navController = navController)
                        }
                    }
                }
            }
        }
    }

}




