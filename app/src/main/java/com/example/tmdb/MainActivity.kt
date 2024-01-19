
package com.example.tmdb

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tmdb.ui.theme.TMDBTheme
import com.example.tmdb.ui.viewmodel.PopularMovieViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TMDBTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .padding(15.dp)
                        .background(color = Color.DarkGray)
                ) {
                    Greeting()
                }
            }
        }
    }
}
@Composable
fun Greeting() {

    val viewModel = PopularMovieViewModel()

    viewModel.getPopularMovies()
    
    val uiState by viewModel.uiState.collectAsState()
    
    Box(
        Modifier
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = Color.Blue)
            .padding(20.dp)) {
        /*Column {
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceAround) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "qualquer coisa"
                )

                Column {
                    Text(text = "sdas")
                    Text(text = "sdas")
                    Text(text = "sdas")
                    Text(text = "sdas")
                }
            }
            Box(modifier = Modifier.height(10.dp))
            Divider()
            Box(modifier = Modifier.height(10.dp))
            Text(text = "aqui fica o overview sdjklasdlksajdlaksjdlaskdjslkajdslakjdlkajldksajldksajlkdsajlkdsajldkasjlkdsajlkdsajlkdsajlkdsajlkdsajlkdsajlkadjslkjdaslkdjsalkjdsaljdsajdlaskjdsalkjdasl")

        }*/
        // vou indo la que tenho que terminar uns role
        LazyColumn {
            items(uiState.popularMovies.orEmpty()) { medias ->
                Box(modifier = Modifier
                    .clip(shape = RoundedCornerShape(20.dp))
                    .padding(20.dp)
                ) {
                    Column {
                        Row(modifier = Modifier.fillMaxWidth(),Arrangement.SpaceAround) {
                            Image(painter = , contentDescription = )
                        }
                    }
                }
            }
            
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TMDBTheme {
        Greeting()
    }
}