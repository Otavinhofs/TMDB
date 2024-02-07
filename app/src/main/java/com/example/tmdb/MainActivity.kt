package com.example.tmdb

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.example.tmdb.ui.theme.TMDBTheme
import com.example.tmdb.ui.viewmodel.PopularMovieViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TMDBTheme {
                // A surface container using the 'background' color from the theme
                Surface(Modifier.background(color = Color.DarkGray).padding(15.dp)) {
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
    LazyColumn (Modifier.background(color = Color.DarkGray)){
        items(uiState.popularMovies.orEmpty()) { medias ->

            Column (Modifier.clip(RoundedCornerShape(25.dp))){
                Box(
                    modifier = Modifier
                        .background(color = Color.Cyan)
                        .fillMaxWidth()
                        .padding(20.dp)
                )
                {
                    Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                        AsyncImage(
                            "https://image.tmdb.org/t/p/w500${medias.poster_path}",
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(50.dp))
                        Column(Modifier.fillMaxWidth()) {
                            Text(
                                text = medias.title,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = medias.vote_average.toString())
                            Text(text = medias.vote_count.toString())
                        }
                    }
                }

            }
            Spacer(modifier = Modifier.height(20.dp))
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