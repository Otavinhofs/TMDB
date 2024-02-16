package com.example.tmdb.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.data.model.Medias
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.update

class PopularMediasViewModel : ViewModel() {

    private val _state = MutableStateFlow(PopularMediasState())
    val uiState = _state.asStateFlow()


    private val repository: TrendingRepository by lazy {
        TrendingRepository()
    }

    fun getPopularMovies() {
        _state.update { it.copy(loading = true) }
        viewModelScope.launch {
            val response = repository.getPopularMovies()
            _state.update { it.copy(trendingMedias = response, loading = false)}
        }

    }

    fun getPopularSeries() {
        _state.update { it.copy(loading = true) }
        viewModelScope.launch {
            val response = repository.getPopularSeries()
            _state.update { it.copy(trendingMedias = response, loading = false)}
        }
    }

    data class PopularMediasState(
        val loading: Boolean = false,
        val error: String? = null,
        val isEmpty: Boolean = false,
        val trendingMedias: List<Medias>? = null
    )
}
