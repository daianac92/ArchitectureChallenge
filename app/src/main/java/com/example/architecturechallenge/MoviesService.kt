package com.example.architecturechallenge

import retrofit2.http.GET

interface MoviesService {
    @GET("discover/movie?api_key=f842e679e5833873206317d0f4714065")
    suspend fun getMovies(): MoviesResponse
}