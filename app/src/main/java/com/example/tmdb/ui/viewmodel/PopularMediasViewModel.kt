package com.example.tmdb.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.data.model.Medias
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.update

class PopularMediasViewModel : ViewModel() {

    val uiState = MutableStateFlow(PopularMediasState())

    // sim, eu fiz pra testar, o correto nesse caso seria assim: blz é assim que chamo os estados
    // sim, eu tinha criado a variavel com popularMovies so blz
    
    private val repository: TrendingRepository by lazy {
        TrendingRepository()
    }

    fun getPopularMovies() {
        viewModelScope.launch {
            val response = repository.getPopularMovies()
            uiState.update { it.copy(trendingMedias = response)}
        }
    }

    fun getPopularSeries() {
        viewModelScope.launch {
            val response = repository.getPopularSeries()
            uiState.update { it.copy(trendingMedias = response, loading = false)}
        }
    }

    data class PopularMediasState(
        val loading: Boolean = false,
        val error: String? = null,
        val isEmpty: Boolean = false,
        val trendingMedias: List<Medias>? = null
    )
}
