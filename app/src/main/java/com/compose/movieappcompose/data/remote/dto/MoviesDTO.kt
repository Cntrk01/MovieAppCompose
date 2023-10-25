package com.compose.movieappcompose.data.remote.dto

import com.compose.movieappcompose.domain.model.Movie

data class MoviesDTO(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)
//burada bir extension oluşturuyoruz.Bunu biz Movie data classına veri vermek için kullanacağız.Sadece gerekli olan bilgileri kullancaz.
//liste varsa map yapmamız gerekiyor liste içindeki verilere erişmek için
//List<Movie> döndürdüğümüz için Movie data classını çağırmak zorundayız.!! search-> den sonra
fun MoviesDTO.toMoviesList() : List<Movie>{
    return Search.map { search -> Movie(search.Poster,search.Title,search.Year,search.imdbID) }
}