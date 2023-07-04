package com.betulkircil.movieapp_clean_arthitecture.presentation.movieDetail

import com.betulkircil.movieapp_clean_arthitecture.domain.model.MovieDetail

data class MovieDetailState(
    val isLoading : Boolean = false,
    val movie : MovieDetail? = null,
    val error : String = ""
)