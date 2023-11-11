package com.example.architecturechallenge.data.remote

import retrofit2.http.GET

interface MoviesService {
    @GET("discover/movie")
    suspend fun getMovies(): MoviesResponse
}