package com.compose.movieappcompose.domain.use_case

import com.compose.movieappcompose.data.remote.dto.toMoviesList
import com.compose.movieappcompose.domain.model.Movie
import com.compose.movieappcompose.domain.repository.MovieRepository
import com.compose.movieappcompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val repository: MovieRepository) {

    fun executeGetMovies(search:String) : Flow<Resource<List<Movie>>> = flow{
        try {
            emit(Resource.Loading())
            val movieList=repository.getMovies(search)
            //Apiden böyle bir veride geliyor bunuda kontrol etmeliyiz çünkü kullanıcı saçma veya olmayan birşey arattığında değeri True dönmeyecek
            //bundan dolayı da veri gelmeyecek.Bunun için kontrol sağlıyoruz ve veri varsa işlem yapcaz.
            if (movieList.Response == "True"){
                emit(Resource.Success(movieList.toMoviesList())) // özel fonksyionu buraya verdik
            }else
                emit(Resource.Error(message = "Movie Not Found !"))
        }
        catch (e:IOException){
            emit(Resource.Error("No Internet Connection"))
        }catch (e: HttpException){
            emit(Resource.Error(message = e.localizedMessage ?: "Error"))
        }
    }
}