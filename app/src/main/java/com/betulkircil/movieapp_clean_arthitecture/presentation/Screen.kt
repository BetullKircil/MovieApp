package com.betulkircil.movieapp_clean_arthitecture.presentation

sealed class Screen(val route : String){
    object MovieListScreen : Screen("movieListScreen")
    object MovieDetailScreen : Screen("movieDetailScreen")
}
