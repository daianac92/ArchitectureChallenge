package com.example.architecturechallenge.ui.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.architecturechallenge.data.MovieModel
import com.example.architecturechallenge.ui.theme.ArchitectureChallengeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen (viewModel: HomeViewModel = hiltViewModel()) {

    ArchitectureChallengeTheme {
        //LiveData
        //val state by viewModel.state.ObserveAsState(MainViewModel.UiState())

        //StateFlow
        val state by viewModel.state.collectAsState()

        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = { TopAppBar(title = { Text(text = "Movies") }) }
            ) { padding ->
                if (state.loading) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                if (state.movies.isNotEmpty()) {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(120.dp),
                        modifier = Modifier.padding(padding),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        items(state.movies) { movie ->

                            MovieItem(
                                movie = movie,
                                onClick = { viewModel.onMovieClick(movie) })

                        }
                    }

                }
            }


        }
    }
}

    @Composable
    fun MovieItem(movie: MovieModel, onClick: () -> Unit) {
        Column(
            modifier = Modifier.clickable(onClick = onClick)
        ) {
            Box {

                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w185/${movie.posterPath}",
                    contentDescription = movie.overview,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(2 / 3f)
                )
                if (movie.favorite) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = movie.title,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp),
                        tint = Color.Red
                    )
                }
            }

            Text(
                text = movie.title,
                modifier = Modifier
                    .padding(8.dp)
                    .height(48.dp),
                maxLines = 2
            )
        }


    }

