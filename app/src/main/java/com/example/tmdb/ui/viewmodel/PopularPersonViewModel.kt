package com.example.tmdb.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.data.model.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularPersonViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(PopularPersonState())
    val uiState = _state.asStateFlow()

    private val repository: TrendingRepository by lazy {
        TrendingRepository()
    }

    fun getPopularPerson() {
        _state.update { it.copy(loading = true) }
        viewModelScope.launch {
            val response = repository.getPopularPerson()
            _state.update { it.copy(trendingPersons = response, loading = false)}
        }
    }
}

data class PopularPersonState (
    val loading: Boolean = false,
    val error: String? = null,
    val isEmpty: Boolean = false,
    val trendingPersons: List<Person>? = null
)
