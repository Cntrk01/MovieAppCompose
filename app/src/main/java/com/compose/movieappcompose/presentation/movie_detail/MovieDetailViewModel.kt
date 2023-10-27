package com.compose.movieappcompose.presentation.movie_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.movieappcompose.domain.use_case.GetMovieDetailUseCase
import com.compose.movieappcompose.util.Constants
import com.compose.movieappcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val stateHandle: SavedStateHandle//bu bize otomatik inject ediliyor.
    //bunun kullanım amacı şimdi ben init içinde fonksiyonu çağırınca benden parametre istiyor ve ben parametreyi uidan vermek istemiyorum burdan vercem
    //bunun için ben argüman gönderirken route kullandım.Routede ben id gönderdiğim için bunu stateHandle bana alıp getiricek ve fonksiyona vericek
    //bu yapı viewmodel içerisinde kullanılan bir yapı savedstatehandle.
    //biz main activity içinde
    //composable(route = Screen.MovieDetailScreen.route+"/${Constants.IMDB_ID}") detail sayfasının bir id isticeni belirttik.
    //MovieScreende iteme tıklanılınca navController.navigate(Screen.MovieDetailScreen.route+"/${movie.imdbID}") bir id istediğini zaten mainde söyledik burda da gelen idyi verdik
)
    :ViewModel(){

    private val _state = mutableStateOf(MovieDetailState())
    val state : State<MovieDetailState> = _state

    init {
        stateHandle.get<String>(Constants.IMDB_ID)?.let {
            getMovieDetail(it)
        }
    }

    private fun getMovieDetail(imdbId:String){
        getMovieDetailUseCase.executeGetMovieDetails(imdbId).onEach {
            when(it){
                is Resource.Success->{
                    _state.value= MovieDetailState(movieDetail = it.data)
                }
                is Resource.Loading->{
                    _state.value= MovieDetailState(isLoading =true)
                }
                is Resource.Error->{
                    _state.value= MovieDetailState(errorMessage = it.message ?: "Error !")
                }
            }
        }.launchIn(viewModelScope)
    }
}