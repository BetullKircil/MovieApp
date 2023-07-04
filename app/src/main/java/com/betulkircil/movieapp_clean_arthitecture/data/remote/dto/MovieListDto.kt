package com.betulkircil.movieapp_clean_arthitecture.data.remote.dto

data class MovieListDto(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)