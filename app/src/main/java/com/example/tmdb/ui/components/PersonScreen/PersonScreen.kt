package com.example.tmdb.ui.components.PersonScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Stars
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.example.tmdb.data.model.Person
import com.example.tmdb.ui.components.TextInfos
import com.example.tmdb.ui.viewmodel.PopularPersonViewModel

@Composable
fun PersonScreen(viewModel: PopularPersonViewModel = hiltViewModel()) {

    LaunchedEffect(Unit){
        viewModel.getPopularPerson()
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
            itemsIndexed(uiState.trendingPersons.orEmpty()) {index, persons ->
                PersonCard(persons)
                Spacer(modifier = Modifier.height(20.dp))
            }

        }
    }

}

@Composable
fun PersonCard(person: Person) {
    Box(
        Modifier
            .clip(RoundedCornerShape(25.dp))
            .background(color = Color.White)
            .fillMaxWidth()
    ) {
        Row(Modifier.fillMaxSize()) {
            Box(
                Modifier
                    .fillMaxHeight()
                    .width(140.dp).align(CenterVertically),
                contentAlignment = Alignment.Center
            ) {
                if (person.profilePath != null) {
                    SubcomposeAsyncImage(
                        model = "https://image.tmdb.org/t/p/w400${person.profilePath}",
                        contentDescription = null,
                        loading = {
                                CircularProgressIndicator()
                        }
                    )
                } else {
                    Icon(
                        Icons.Rounded.Person, contentDescription = null, modifier = Modifier
                            .width(110.dp)
                            .height(150.dp)
                    )
                }

            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                TextInfos(
                    text = person.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(
                    modifier = Modifier.height(20.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Rounded.Stars,
                        contentDescription = "Stars",
                        modifier = Modifier.height(20.dp),
                        tint = Color.Yellow
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    TextInfos(
                        text = person.popularity.toString()
                    )
                }
                Spacer(
                    modifier = Modifier.height(20.dp)
                )
                TextInfos(
                    text = person.knowForDepartment,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}