package com.example.architecturechallenge.data.remote

import com.example.architecturechallenge.data.MovieModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {
    suspend fun getMovies(): List<MovieModel> {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesService::class.java)
            .getMovies()
            .results
            .map { it.toMovieModel() }

    }
}
