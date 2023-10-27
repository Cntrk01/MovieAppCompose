package com.compose.movieappcompose.presentation.movie

import com.compose.movieappcompose.domain.model.Movie

data class MovieState(
    val isLoading : Boolean=false,
    val movies : List<Movie> = emptyList(),
    val error : String = "",
    val search : String = "Batman"
)