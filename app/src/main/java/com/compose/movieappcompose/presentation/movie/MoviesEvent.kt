package com.compose.movieappcompose.presentation.movie

sealed class MoviesEvent {
    //Bu şekilde tanımladıgımızda veri kayıt edebiliyoruz buraya
    data class Search(val searchString: String) : MoviesEvent()
}