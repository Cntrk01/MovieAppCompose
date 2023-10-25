package com.compose.movieappcompose.presentation.movie

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.movieappcompose.domain.use_case.GetMovieUseCase
import com.compose.movieappcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val getMovieUseCase: GetMovieUseCase) : ViewModel() {

    private val _state = mutableStateOf(MovieState()) //buda mutable state oluyor
    val state : State<MovieState> = _state //inmutable state oluştururken State ile oluşturabiliyoruz.Livedata alternatifi

    private var job : Job?=null

    init {
        getMovies(_state.value.search)
    }

    private fun getMovies(search:String){
        //bunu yapma sebebimiz ise örneğin kullanıcı arama yaptı hala sonuç gelmedi.Farklı bir arama işlemi gerçekleştiğinde bu önceki işlemi iptal etmek isteyebiliriz bunun için job kullanabiliriz.!!!!!
        job?.cancel()
        //onEach ile her veri geldiğinde işlem yapmamızı sağlıyor.
        job=getMovieUseCase.executeGetMovies(search = search).onEach {
            when(it){
                //durumlara göre MovieState içerisine veri yüklemesi yapıyoruz
                is Resource.Success ->{
                    _state.value= MovieState(movies = it.data ?: emptyList())
                }
                is Resource.Loading ->{
                    //_state.value=_state.value.copy() istersek MovieState kullanmadan copy aracılığı ile de referans verilen classın değişkenlerine değer verilebilir
                    _state.value= MovieState(isLoading = true)
                }
                is Resource.Error ->{
                    _state.value= MovieState(isLoading = false, error = it.message ?: "Error !")
                }
            }
        }.launchIn(viewModelScope)
    }

    //kullanıcı etkileşime geçtiği zaman çağırılacak bir fonksiyon.Kullanıcı birden fazla yerde etkileşime geçebilir
    //biz sadece kullanıcıyla search işlemi oldugunda etkileşime giriyoruz
    fun onEvent(event: MoviesEvent){
        when(event){
            //daha fazla varsa buraya is diyip devam edebiliriz
            is MoviesEvent.Search->{//nurada Search fonksiyonuna eriştiğimiz için searchString değişkenine erişim sağlayabiliyoruz.
                getMovies(event.searchString)
            }
        }
    }
}