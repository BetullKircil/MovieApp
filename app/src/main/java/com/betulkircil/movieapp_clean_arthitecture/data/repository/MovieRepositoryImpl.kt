package com.betulkircil.movieapp_clean_arthitecture.data.repository

import com.betulkircil.movieapp_clean_arthitecture.data.remote.MovieApi
import com.betulkircil.movieapp_clean_arthitecture.data.remote.dto.MovieDetailDto
import com.betulkircil.movieapp_clean_arthitecture.data.remote.dto.MovieListDto
import com.betulkircil.movieapp_clean_arthitecture.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : MovieRepository{
    override suspend fun getMovies(search: String): MovieListDto {
        return api.getMovieList(search)
    }

    override suspend fun getMovieDetail(imdbId: String): MovieDetailDto {
        return api.getMovieDetail(imdbId)
    }
}