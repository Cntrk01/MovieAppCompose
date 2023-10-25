package com.compose.movieappcompose.data.remote

import com.compose.movieappcompose.data.remote.dto.MovieDetailDTO
import com.compose.movieappcompose.data.remote.dto.MoviesDTO
import com.compose.movieappcompose.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    //https://www.omdbapi.com/?apikey=4d0dbb24&i=tt0372784 /dan sonra parametre olmadıgı için . koyuyoruz örneğin /v1/?apikey=4d0dbb24&i=tt0372784 olsaydı v1 dicektik . yerine
    @GET(".")
    suspend fun getMovies(
        @Query("s") searchString:String,
        @Query("apikey") apikey :String = API_KEY
    ) : MoviesDTO
    //imdbid dökümantasyonda i şekilinde yazıyor buraya vericez.
    @GET(".")
    suspend fun getMoviesDetail(
        @Query("i") imdbId:String,
        @Query("apikey") apikey :String = API_KEY
    ) : MovieDetailDTO
}