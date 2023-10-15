package com.example.architecturechallenge.data

import com.example.architecturechallenge.data.local.LocalDataSource
import com.example.architecturechallenge.data.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow
class MoviesRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    val movies: Flow<List<MovieModel>> = localDataSource.movies

    suspend fun updateMovie(movie: MovieModel) {
        localDataSource.updateMovie(movie)
    }

    suspend fun requestMovies() {
        val isDbEmpty = localDataSource.count() == 0
        if (isDbEmpty) {
            localDataSource.insertAll(remoteDataSource.getMovies())
        }

    }
}