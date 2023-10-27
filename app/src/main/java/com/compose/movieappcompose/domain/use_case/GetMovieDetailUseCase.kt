package com.compose.movieappcompose.domain.use_case

import com.compose.movieappcompose.data.remote.dto.toMovieDetail
import com.compose.movieappcompose.data.remote.dto.toMoviesList
import com.compose.movieappcompose.domain.model.Movie
import com.compose.movieappcompose.domain.model.MovieDetail
import com.compose.movieappcompose.domain.repository.MovieRepository
import com.compose.movieappcompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val repository: MovieRepository) {
    fun executeGetMovieDetails(imdbId:String) : Flow<Resource<MovieDetail>> = flow{
        try {
            emit(Resource.Loading())
            val movieList=repository.getMovieDetail(imdbId)
            emit(Resource.Success(movieList.toMovieDetail())) // özel fonksyionu buraya verdik
        }
        catch (e: IOException){
            emit(Resource.Error("No Internet Connection"))
        }catch (e: HttpException){
            emit(Resource.Error(message = e.localizedMessage ?: "Error"))
        }
    }
}