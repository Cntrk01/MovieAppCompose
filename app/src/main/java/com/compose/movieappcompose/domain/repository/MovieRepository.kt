package com.compose.movieappcompose.domain.repository

import com.compose.movieappcompose.data.remote.dto.MovieDetailDTO
import com.compose.movieappcompose.data.remote.dto.MoviesDTO

interface MovieRepository {
    suspend fun getMovies(search:String) : MoviesDTO
    suspend fun getMovieDetail(imdbId:String) : MovieDetailDTO
}