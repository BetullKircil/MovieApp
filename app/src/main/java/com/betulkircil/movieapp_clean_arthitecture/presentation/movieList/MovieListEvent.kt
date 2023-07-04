package com.betulkircil.deneme.presentation.movie_list

sealed class MovieListEvent {
    data class Search(val searchString : String) : MovieListEvent()

}