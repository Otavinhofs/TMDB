package com.example.tmdb.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.tmdb.Utils.MediaType
import com.example.tmdb.data.model.Medias
import com.example.tmdb.ui.viewmodel.PopularMediasViewModel
import java.text.SimpleDateFormat

@Composable
fun MediasScreen(mediaType: MediaType, viewModel: PopularMediasViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        if (mediaType == MediaType.MOVIE) {
            viewModel.getPopularMovies()
        } else {
            viewModel.getPopularSeries()
        }
    }
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.loading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            Modifier
                .background(color = Color.LightGray)
                .padding(15.dp)
                .fillMaxSize()
        ) {
            items(uiState.trendingMedias.orEmpty()) { medias ->
                MediasCard(medias)
            }
        }
    }
}

@Composable
fun MediasCard(medias: Medias) {

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
                    "https://image.tmdb.org/t/p/w400${medias.posterPath}",
                    contentDescription = null

                )
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {

                    TextInfos(
                        text = if (medias.media_type == "movie") {
                            medias.title
                        } else medias.name,
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
                        TextInfos(text = (if (medias.media_type == "movie") {
                            medias.release_date
                        } else medias.first_air_date)?.let {
                            SimpleDateFormat("dd/MM/yyyy")
                                .format(it).toString()
                        })
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
                            text = "%.2f".format(medias.vote_average)
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(5.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        Icon(
                            Icons.Rounded.Person,
                            contentDescription = "person",
                            modifier = Modifier.height(20.dp),
                            tint = Color.Blue
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        TextInfos(
                            text = (medias.vote_count).toString(),
                            fontSize = 15.sp
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(Color.Black)
                    .padding(horizontal = 20.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                TextInfos(text = medias.overview)
            }

        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}


@Composable
fun TextInfos(
    fontSize: TextUnit = TextUnit.Unspecified,
    text: String?,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Text(
        text = (text).toString(),
        fontSize = fontSize,
        fontWeight = fontWeight,
        color = Color.Black,
    )
}