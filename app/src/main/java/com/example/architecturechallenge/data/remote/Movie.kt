package com.example.architecturechallenge.data.remote

import com.example.architecturechallenge.data.MovieModel

data class Movie(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    val favorite: Boolean
)

fun Movie.toMovieModel() = MovieModel(
    id = id,
    title = title,
    overview = overview,
    posterPath = poster_path,
    favorite = favorite
)