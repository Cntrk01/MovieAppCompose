package com.compose.movieappcompose.presentation.movie_detail

import com.compose.movieappcompose.domain.model.MovieDetail

data class MovieDetailState (
    val isLoading : Boolean=false,
    val movieDetail: MovieDetail ?=null,
    val errorMessage : String ?=null
)