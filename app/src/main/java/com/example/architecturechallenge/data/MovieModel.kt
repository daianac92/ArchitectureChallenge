package com.example.architecturechallenge.data

data class MovieModel (
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val favorite: Boolean = false
)