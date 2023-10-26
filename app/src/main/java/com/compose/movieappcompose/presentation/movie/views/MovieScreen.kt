package com.compose.movieappcompose.presentation.movie.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.compose.movieappcompose.domain.model.Movie
import com.compose.movieappcompose.presentation.movie.MoviesViewModel

@Composable
fun MovieScreen(
    navController : NavController,
    viewModel : MoviesViewModel = hiltViewModel()
){
    //viewmodelde veri verdiğimiz class bu . burda hata durumu vs hepsini yönetebiliriz.
    val state=viewModel.state.value
    
    Box(modifier = Modifier.fillMaxSize().background(Color.Black)){

    }
}

@Preview
@Composable
fun Screen(){
    MovieScreen(navController = rememberNavController())
}