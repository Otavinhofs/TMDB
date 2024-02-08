package com.example.tmdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Stars
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
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.example.tmdb.data.model.Medias
import com.example.tmdb.ui.theme.TMDBTheme
import com.example.tmdb.ui.viewmodel.PopularMovieViewModel
import java.math.RoundingMode
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TMDBTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    Modifier
                        .background(color = Color.LightGray)
                        .padding(15.dp)
                ) {
                    MediasCard()
                }
            }
        }
    }
}

@Composable
fun MediasCard() {

    val viewModel = PopularMovieViewModel()

    viewModel.getPopularMovies()

    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(Modifier.background(color = Color.LightGray)) {
        items(uiState.popularMovies.orEmpty()) { medias ->

            Box(
                Modifier
                    .clip(RoundedCornerShape(25.dp))
                    .background(color = Color.White)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .background(color = Color.White)
                        .fillMaxWidth()

                )
                {
                    Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceAround) {
                        AsyncImage(
                            "https://image.tmdb.org/t/p/w400${medias.poster_path}",
                            contentDescription = null

                        )


                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(15.dp)) {

                            TextInfos(
                                medias = medias.title,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(15.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                Arrangement.Start
                            ) {
                                Icon(
                                    Icons.Rounded.CalendarMonth,
                                    contentDescription = "Calendar Mounth",
                                    modifier = Modifier.height(20.dp),
                                    tint = Color.Red
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                TextInfos(
                                    medias = SimpleDateFormat("dd/MM/yyyy")
                                        .format(medias.release_date)
                                        .toString()
                                )
                            }

                            Spacer(
                                modifier = Modifier.height(5.dp)
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),

                                ) {
                                Icon(
                                    Icons.Rounded.Stars,
                                    contentDescription = "Stars",
                                    modifier = Modifier.height(20.dp),
                                    tint = Color.Yellow
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                TextInfos(
                                    medias = "%.2f".format(medias.vote_average)
                                )
                            }

                            Spacer(
                                modifier = Modifier.height(5.dp)
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(
                                    Icons.Rounded.Person,
                                    contentDescription = "person",
                                    modifier = Modifier.height(20.dp),
                                    tint = Color.Blue
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                TextInfos(
                                    medias = (medias.vote_count).toString(),
                                )
                            }
                        }
                    }

                    Box(modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(Color.Black)
                        .padding(horizontal = 20.dp))

                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)) {
                        TextInfos(medias = medias.overview)
                    }

                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun TextInfos(
    fontSize: TextUnit = TextUnit.Unspecified,
    medias: String?,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Text(
        text = (medias).toString(),
        fontSize = fontSize,
        fontWeight = fontWeight,
        color = Color.Black
    )
}