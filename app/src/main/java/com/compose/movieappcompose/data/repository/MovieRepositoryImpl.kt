package com.compose.movieappcompose.data.repository

import com.compose.movieappcompose.data.remote.MovieApi
import com.compose.movieappcompose.data.remote.dto.MovieDetailDTO
import com.compose.movieappcompose.data.remote.dto.MoviesDTO
import com.compose.movieappcompose.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api: MovieApi): MovieRepository{

    override suspend fun getMovies(search: String): MoviesDTO {
        return api.getMovies(searchString = search)
    }

    override suspend fun getMovieDetail(imdbId: String): MovieDetailDTO {
        return api.getMoviesDetail(imdbId = imdbId)
    }
}