package com.betulkircil.movieapp_clean_arthitecture.domain.repository

import com.betulkircil.movieapp_clean_arthitecture.data.remote.dto.MovieDetailDto
import com.betulkircil.movieapp_clean_arthitecture.data.remote.dto.MovieListDto
import com.betulkircil.movieapp_clean_arthitecture.data.remote.dto.Search

interface MovieRepository {
    suspend fun getMovies(search: String) : MovieListDto
    suspend fun getMovieDetail(imdbId : String) : MovieDetailDto
}