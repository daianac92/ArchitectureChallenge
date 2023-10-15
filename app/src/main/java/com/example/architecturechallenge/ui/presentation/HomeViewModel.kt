package com.example.architecturechallenge.ui.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecturechallenge.data.MovieModel
import com.example.architecturechallenge.data.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MoviesRepository) : ViewModel() {

    //LiveData
    /*private val _state = MutableLiveData(UiState())
     val state: LiveData<UiState> = _state*/

    //StateFlow
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            repository.requestMovies()

            repository.movies.collect { movies ->
                _state.value = UiState(
                    movies = movies
                )
            }
        }

    }

fun onMovieClick(movie: MovieModel) {
    viewModelScope.launch {
        repository.updateMovie(movie.copy(favorite = !movie.favorite))
    }
}

data class UiState(
    val loading: Boolean = false,
    val movies: List<MovieModel> = emptyList()
)

}
