package com.example.architecturechallenge.data.remote

import android.util.Log
import com.example.architecturechallenge.data.MovieModel
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val service: MoviesService) {
    suspend fun getMovies(): List<MovieModel> {


        return try {
            service.getMovies()
                .results
                .map { it.toMovieModel() }
        } catch (e: Exception) {
            Log.d("DAIANA", "error ")
            emptyList()
        }

    }

}
