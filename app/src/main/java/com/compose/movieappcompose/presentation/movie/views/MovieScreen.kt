package com.compose.movieappcompose.presentation.movie.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.compose.movieappcompose.presentation.movie.MoviesEvent
import com.compose.movieappcompose.presentation.movie.MoviesViewModel

@Composable
fun MovieScreen(
    navController : NavController,
    viewModel : MoviesViewModel = hiltViewModel()
){
    //viewmodelde veri verdiğimiz class bu . burda hata durumu vs hepsini yönetebiliriz.
    val state=viewModel.state.value
    
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)){
        Column {
            MovieSearchBar(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
                hint = "Batman",
                onSearch = {
                    viewModel.onEvent(MoviesEvent.Search(it))
                })
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(state.movies){movie->
                    Text(text = movie.Title,modifier=Modifier.fillMaxWidth(), textAlign = TextAlign.Center, color = Color.White)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieSearchBar(
    modifier: Modifier,
    hint:String ="",
    onSearch : (String)->Unit={}
){
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplay by remember {
        //boş olmadığında hint gösterilecek
        mutableStateOf(hint !="")
    }
    Box(modifier = modifier){
        TextField(value = text, onValueChange = {
            text=it
        },//burada kullanıcı klavyede yazıp okey tusuna bastıgında onSearch methoduna yazılan stringi veriyoruz onuda yukarda viewmodele gönderiyoruz
            keyboardActions = KeyboardActions(onDone = {//burada unit dönüyor yani kullanıcı okey tusuna bastıgında içinde olmasını istediğimiz işlemleri yapıyoruz.
                onSearch(text)
            }), maxLines = 1,
                singleLine = true,
                textStyle = TextStyle(color = Color.Black),
                shape = RoundedCornerShape(12.dp),

                modifier= Modifier
                    .fillMaxWidth()
                    .shadow(5.dp, CircleShape)
                    .background(Color.White, CircleShape)
                    .padding(20.dp)
                    //bu method kullanıcı TextField e tıkladığı anda aktif oluyor.Buraya fonksiyondan parametre veriyoruz.Hint geldiği için true dönüyor.
                    .onFocusChanged {
                        isHintDisplay = it.isFocused != true && text.isEmpty()
                    }
        )
//        if (isHintDisplay){
//            Text(text = hint,
//                color = Color.LightGray,
//                modifier=Modifier.padding(horizontal = 20.dp, vertical = 12.dp))
//        }
    }
}