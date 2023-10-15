package com.example.architecturechallenge.data.local

import com.example.architecturechallenge.data.MovieModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalDataSource(private val dao: MoviesDao) {

    val movies: Flow<List<MovieModel>> =
        dao.getMovies().map { movies -> movies.map { it.toMovieModel() } }

    suspend fun updateMovie(movie: MovieModel) {
        dao.updateMovie(movie.toLocalMovie())
    }

    suspend fun insertAll(movies: List<MovieModel>) {
        dao.insertMovies(movies.map { it.toLocalMovie() })
    }

    suspend fun count(): Int {
        return dao.count()
    }
}