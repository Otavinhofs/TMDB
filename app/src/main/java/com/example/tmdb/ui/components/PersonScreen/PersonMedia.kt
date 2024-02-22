package com.example.tmdb.ui.components.PersonScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tmdb.ui.components.MediasCard
import com.example.tmdb.ui.viewmodel.PopularPersonViewModel

@Composable
fun PersonMovieScreen(viewModel: PopularPersonViewModel = hiltViewModel()) {


    viewModel.getPopularPerson()


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
            items(uiState.trendingPersons.orEmpty()) { person ->
                person.knowFor.forEach { media ->
                    media?.let { MediasCard(media = it) }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

        }
    }
}


