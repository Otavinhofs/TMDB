package com.example.tmdb.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.data.model.Person
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PopularPersonViewModel : ViewModel() {
    val uiState = MutableStateFlow(PopularPersonState())
    private val repository: TrendingRepository by lazy {
        TrendingRepository()
    }

    fun getPopularPerson() {
        viewModelScope.launch {
            val response = repository.getPopularPerson()
            uiState.update { it.copy(trendingPersons = response)}
        }
    }
}

data class PopularPersonState (
    val loading: Boolean = false,
    val error: String? = null,
    val isEmpty: Boolean = false,
    val trendingPersons: List<Person>? = null
)
