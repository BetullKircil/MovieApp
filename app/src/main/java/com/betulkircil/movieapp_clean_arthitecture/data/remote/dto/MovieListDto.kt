package com.betulkircil.movieapp_clean_arthitecture.data.remote.dto

import com.betulkircil.movieapp_clean_arthitecture.domain.model.MovieList

data class MovieListDto(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)

fun MovieListDto.toMovieList() : List<MovieList>{
    return Search.map {
            search -> MovieList(search.Poster, search.Title, search.Year, search.imdbID)
    }
}