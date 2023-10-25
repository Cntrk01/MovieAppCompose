package com.compose.movieappcompose.data.remote.dto

data class MoviesDTO(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)